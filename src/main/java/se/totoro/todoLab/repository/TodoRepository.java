package se.totoro.todoLab.repository;

import org.springframework.data.repository.CrudRepository;
import se.totoro.todoLab.model.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long>{
}
