package se.totoro.todoLab.model;

import javax.persistence.*;

@Entity
public final class Todo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String priority;

    @ManyToOne
    private User user;

    protected Todo(){
    }

    public Todo(String description, String priority){
        this.description = description;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public User getUser() {
        return user;
    }

    public void assignUser(User user){
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("Id:%s, description:%s, priority:%s", id, description, priority);
    }
}
