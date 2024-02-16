package com.example.TaskMaster.Model;

import jakarta.persistence.*;

public class User_Task {
    @Id
    @SequenceGenerator(name = "userTask_sequence", sequenceName = "userTask_sequence", allocationSize = 1)
    private Long id;

    @ManyToMany
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

}
