package se.totoro.todolab.resource;

import org.springframework.stereotype.Component;
import se.totoro.todolab.model.Todo;
import se.totoro.todolab.service.TodoService;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.*;

@Component
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Path("todos")
public final class TodoResource {

    @Context
    UriInfo uriInfo;

    private final TodoService service;

    public TodoResource(TodoService service) {
        this.service = service;
    }

    @POST
    public Response createTodo(Todo todo) {
        Todo result = service.createTodo(todo);

        return Response.created(URI.create(uriInfo
                .getAbsolutePathBuilder()
                .path(result.getId().toString())
                .toString()))
                .build();
    }

    @GET
    @Path("{id}")
    public Response getTodo(@PathParam("id") Long id) {
        return service.getTodoById(id)
                .map(t -> Response.ok(t))
                .orElse(Response.status(NOT_FOUND))
                .build();
    }

    @GET
    public Response getAllTodos(@QueryParam("user") Long userId, @QueryParam("priority") Integer priority) {
        List<Todo> todos = service.getAllTodos();
        if(userId == null) {
            return Response.ok(todos).build();
        }

        Stream<Todo> todoStream = todos.stream()
                .filter(todo -> todo.getUser() != null)
                .filter(todo -> todo.getUser().getId().equals(userId));
        if(priority != null){
            todoStream.filter(todo -> todo.getPriority().equals(priority));
        }
        return Response.ok(todoStream.collect(Collectors.toList())).build();
    }



    /*@GET
    public Response getAll(@QueryParam("limit") @DefaultValue("5") int limit,
                           @QueryParam("sort") @DefaultValue("asc") String sort,
                           @QueryParam("links") @DefaultValue("false") boolean links) {
        List<Customer> customers = service.getAllCustomers(limit, sort.equals("desc"));

        if (links) {
            List<String> customerLinks = customers.stream().map(c ->
                    uriInfo.getAbsolutePathBuilder()
                            .path(c.getId().toString())
                            .build().toString())
                    .collect(Collectors.toList());

            return Response.ok(customerLinks).build();
        }

        return Response.ok(customers).build();
    }*/

    @DELETE
    @Path("{id}")
    public Response deleteTodo(@PathParam("id") Long id) {
        return service.deleteTodoById(id)
                .map(t -> Response.status(NO_CONTENT))
                .orElse(Response.status(NOT_FOUND))
                .build();
    }

    @PUT
    @Path("{id}")
    public Response assignUserToTodo(@QueryParam("user") @NotNull Long userId, @PathParam("id") Long id) {
        return service.assignUserToTodo(id, userId)
                .map(t -> Response.status(NO_CONTENT))
                .orElse(Response.status(NOT_FOUND))
                .build();
    }

}
