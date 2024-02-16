package com.example.TaskMaster.Repository;

import com.example.TaskMaster.Model.Task;
import com.example.TaskMaster.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {

    Optional<Task> findAllByTitle(String title);
    List<Task> findByUsers(User user);
}
