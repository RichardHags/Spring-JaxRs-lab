package se.totoro.todoLab.service;

import org.springframework.stereotype.Service;
import se.totoro.todoLab.model.User;
import se.totoro.todoLab.repository.UserRepository;
import se.totoro.todoLab.service.exceptions.InvalidUserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public final class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public User createUser(User user){
        validate(user);
        return repository.save(new User(user.getFirstName(), user.getLastName()));
    }

    public Optional<User> getUserById(Long id){
        return repository.findById(id);
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        repository.findAll().forEach(users::add);

        return users;
    }

    public Optional<User> deleteUserById(Long id){
        Optional<User> temp = repository.findById(id);

        if(temp.isPresent()){
            repository.deleteById(id);
        }
        return temp;
    }

    private void validate(User user){
        if(user.getFirstName() == null || user.getLastName() == null) {
            throw new InvalidUserException("You must type something");
        }
    }



}
