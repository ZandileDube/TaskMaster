package com.example.TaskMaster.Model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "User_sequence")
    @SequenceGenerator(name = "User_sequence",
                       sequenceName = "user_sequence",
                       allocationSize = 1)
    private Long userId;
    private String username;
    private String password;
    private String role;
    @ManyToMany(mappedBy = "users")
    private Set<Task> tasks = new HashSet<>();

    public User(Long userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}



