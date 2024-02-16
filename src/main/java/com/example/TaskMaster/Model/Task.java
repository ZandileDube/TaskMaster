package com.example.TaskMaster.Model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "task_sequence")
    @SequenceGenerator(name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1)
    private Long taskId;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private taskStatus status;
    private LocalDate due_date;
    @ManyToMany
    @JoinTable(
            name = "user_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    public Task() {
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Task(Long taskId, String title, String description, taskStatus status, LocalDate due_date, Set<User> users) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.due_date = due_date;
        this.users = users;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public taskStatus getStatus() {
        return status;
    }

    public void setStatus(taskStatus status) {
        this.status = status;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", due_date=" + due_date +
                '}';
    }
}

