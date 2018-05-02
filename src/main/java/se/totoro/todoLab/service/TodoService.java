package se.totoro.todoLab.service;

import org.springframework.stereotype.Service;
import se.totoro.todoLab.model.Todo;
import se.totoro.todoLab.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService (TodoRepository repository){
        this.repository = repository;
    }

    public Todo createTodo(Todo todo){
        //Ha en koll här, som kollar att det stämmer?
        return repository.save(new Todo(todo.getDescription(), todo.getPriority()));
    }

    public Optional<Todo> getTodoById(Long id) {
        return repository.findById(id);
    }

    public List<Todo> getAllTodos(){
        List<Todo> todos = new ArrayList<>();
        repository.findAll().forEach(todos::add);

        return todos;
    }

    public Optional<Todo> deleteTodoById(Long id) {
        Optional<Todo> temp = repository.findById(id);

        if (temp.isPresent()) {
            repository.deleteById(id);
        }
        return temp;
    }
}
