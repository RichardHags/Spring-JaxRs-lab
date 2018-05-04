package se.totoro.todolab.repository;

import org.springframework.data.repository.CrudRepository;
import se.totoro.todolab.model.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {

}
