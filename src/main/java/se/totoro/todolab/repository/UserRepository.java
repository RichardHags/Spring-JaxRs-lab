package se.totoro.todolab.repository;

import org.springframework.data.repository.CrudRepository;
import se.totoro.todolab.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
