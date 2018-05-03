package se.totoro.todoLab.service.exceptions;

public final class InvalidTodoException extends RuntimeException{

    public InvalidTodoException(String message){
        super(message);
    }
}
