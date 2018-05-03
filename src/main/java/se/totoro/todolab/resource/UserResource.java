package se.totoro.todolab.resource;

import org.springframework.stereotype.Component;
import se.totoro.todolab.model.User;
import se.totoro.todolab.service.UserService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.net.URI;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.*;

@Path("users")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Component
public final class UserResource {

    @Context
    UriInfo uriInfo;

    private final UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }

    @POST                   //@Valid
    public Response createUser(User user) {
        User result = service.createUser(user);

        return Response.created(URI.create(uriInfo
                .getAbsolutePathBuilder()
                .path(result.getId().toString())
                .toString()))
                .build();
    }

    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") Long id) {

        return service.getUserById(id)
                .map(u -> Response.ok(u))
                .orElse(Response.status(NOT_FOUND))
                .build();
    }

    @GET
    public Response getAllUsers() {
        return Response.ok(service.getAllUsers()).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id) {

        return service.deleteUserById(id)
                .map(u -> Response.status(NO_CONTENT))
                .orElse(Response.status(NOT_FOUND))
                .build();
    }

}