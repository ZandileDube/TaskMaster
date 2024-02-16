package com.example.TaskMaster.Service;

import com.example.TaskMaster.Model.Task;
import com.example.TaskMaster.Model.User;
import com.example.TaskMaster.Repository.TaskRepository;
import com.example.TaskMaster.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void assignTasktoUser(Long taskId, Long userId) {

            Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found"));
            User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
            task.getUsers().add(user);//establish the association
            taskRepository.save(task);//persists changes to the db

    }

    public List<Task> getTasks() {

        return taskRepository.findAll();
    }


    public void removeTaskFromUser(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        task.getUsers().remove(user);
        taskRepository.save(task);
    }

    public List<Task> getAllTasksOfUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return taskRepository.findByUsers(user);
    }


    public void addTask(Task task) {
        Optional <Task> optionalTask = taskRepository.findAllByTitle(task.getTitle());
        if(optionalTask.isPresent()){
            throw new IllegalStateException("Task already exists");
        }
        taskRepository.save(task);
    }
}
