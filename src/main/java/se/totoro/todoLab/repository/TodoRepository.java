package se.totoro.todoLab.repository;

import org.springframework.data.repository.CrudRepository;
import se.totoro.todoLab.model.Todo;
import se.totoro.todoLab.model.User;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends CrudRepository<Todo, Long>{

    List<Todo> getAllByUserId(Long id);

    List<Todo> getAllByPriorityAndId(String priority, Long id);

    Optional<User> getByUserId(Long id);
}
