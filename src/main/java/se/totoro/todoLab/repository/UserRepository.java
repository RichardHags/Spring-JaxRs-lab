package se.totoro.todoLab.repository;

import org.springframework.data.repository.CrudRepository;
import se.totoro.todoLab.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
