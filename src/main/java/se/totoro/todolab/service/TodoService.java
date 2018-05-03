package se.totoro.todolab.service;

import org.springframework.stereotype.Service;
import se.totoro.todolab.model.Todo;
import se.totoro.todolab.model.User;
import se.totoro.todolab.repository.TodoRepository;
import se.totoro.todolab.repository.UserRepository;
import se.totoro.todolab.service.exceptions.InvalidTodoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public final class TodoService {

    private final TodoRepository repository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Todo createTodo(Todo todo) {
        validate(todo);
        return repository.save(new Todo(todo.getDescription(), todo.getPriority()));
    }

    public Optional<Todo> getTodoById(Long id) {
        return repository.findById(id);
    }

    public List<Todo> getAllTodos() {
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

    private void validate(Todo todo) {
        if (todo.getDescription() == null || todo.getPriority() == null || todo.getDescription().isEmpty()) {
            throw new InvalidTodoException("Description and priority have to have values");
        }
    }

    public Optional<Todo> assignTodoToUser(Long id, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Todo> todo = repository.findById(id);

        if (user.isPresent() && todo.isPresent()) {
            Todo temp = todo.get();
            temp.assignUser(user.get());
            repository.save(temp);

            return todo;
        } else {
            throw new InvalidTodoException("Could not find a user or todo");
        }
    }


}
