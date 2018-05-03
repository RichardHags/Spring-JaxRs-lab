package se.totoro.todolab.service.exceptions;

public final class InvalidUserException extends RuntimeException {

    public InvalidUserException(String message) {
        super(message);
    }
}
