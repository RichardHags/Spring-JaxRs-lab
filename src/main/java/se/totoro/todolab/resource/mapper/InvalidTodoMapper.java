package se.totoro.todolab.resource.mapper;

import se.totoro.todolab.service.exceptions.InvalidTodoException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public final class InvalidTodoMapper implements ExceptionMapper<InvalidTodoException> {

    @Override
    public Response toResponse(InvalidTodoException exception) {
        return Response.status(BAD_REQUEST).entity(singletonMap("error", exception.getMessage())).build();
    }
}