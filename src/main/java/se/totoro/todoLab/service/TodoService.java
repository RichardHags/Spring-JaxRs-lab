package se.totoro.todoLab.service;

import org.springframework.stereotype.Service;
import se.totoro.todoLab.model.Todo;
import se.totoro.todoLab.model.User;
import se.totoro.todoLab.repository.TodoRepository;
import se.totoro.todoLab.service.exceptions.InvalidTodoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public final class TodoService {

    private final TodoRepository repository;

    public TodoService (TodoRepository repository){
        this.repository = repository;
    }

    public Todo createTodo(Todo todo){
        validate(todo);
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

    private void validate(Todo todo){
        if (todo.getDescription() == null || todo.getPriority() == null){
            throw new InvalidTodoException("You must type something");
        }
    }

    public Optional<Todo> assignUserToTodo(Long id, Long userId){
        Optional<User> user = repository.getByUserId(userId);
        Optional<Todo> todo = repository.findById(id);

        if(user.isPresent() && todo.isPresent()){
            Todo temp = todo.get();
            temp.assignUser(user.get());
            repository.save(temp);

            return todo;
        } else {
            throw new InvalidTodoException("Did not work");
        }
    }


}
