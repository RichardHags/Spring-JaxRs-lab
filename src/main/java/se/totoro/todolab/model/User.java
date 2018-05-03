package se.totoro.todolab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public final class User {

    @Id
    @GeneratedValue
    private Long id;

    //@NotEmpty
    @Column(nullable = false)
    private String firstName;
    //@NotEmpty
    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Collection<Todo> todos;

    protected User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Collection<Todo> getTodos() {
        return todos;
    }

    @Override
    public String toString() {
        return String.format("Id:%s, firstName:%s, lastName:%s", id, firstName, lastName);
    }
}
