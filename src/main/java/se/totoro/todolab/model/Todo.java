package se.totoro.todolab.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public final class Todo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer priority;

    @ManyToOne
    @JsonBackReference
    private User user;

    protected Todo() {
    }

    public Todo(String description, Integer priority) {
        this.description = description;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriority() {
        return priority;
    }

    public User getUser() {
        return user;
    }

    public void assignUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("Id:%s, description:%s, priority:%s", id, description, priority);
    }
}
