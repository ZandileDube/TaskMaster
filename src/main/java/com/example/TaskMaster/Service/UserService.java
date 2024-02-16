package com.example.TaskMaster.Service;

import com.example.TaskMaster.Model.User;
import com.example.TaskMaster.Repository.TaskRepository;
import com.example.TaskMaster.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public void addUser(User user) {
        Optional <User> optionalUser = userRepository.findAllByUsername(user.getUsername());
        if(optionalUser.isPresent()){
            throw new IllegalStateException("username taken");
        }
        userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new IllegalStateException("User not found with id " + userId));

    }
    @Transactional
    public void updateRole(Long userId, String newRole) {
        User user = userRepository.findUserByUserId(userId).orElseThrow(() -> new IllegalStateException(
                "User with id" + userId + "does not exist"));
        // Validate the new Role and Persist the updated user entity
        if (newRole != null && !newRole.isEmpty() && !Objects.equals(user.getRole(), newRole)) {
            user.setRole(newRole);
            userRepository.save(user);

        }

    }

    @Transactional
    public void updatePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist"));

        // Validate the new password
        if (newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        // Update the user's password if it's different from the current one
        if (!Objects.equals(user.getPassword(), newPassword)) {
            user.setPassword(newPassword);
            userRepository.save(user); // Persist the updated user entity
        }
    }

    @Transactional
    public void updateUsername(Long userId, String newUsername) {
        User user = userRepository.findUserByUserId(userId).orElseThrow(() -> new IllegalStateException("User with id" + userId + "does not exist"));
        // Validate the new username
        if (newUsername == null || newUsername.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        // Update the user's username if it's different from the current one
        if (!Objects.equals(user.getUsername(), newUsername)) {
            user.setPassword(newUsername);
            userRepository.save(user); // Persist the updated user entity
        }
    }


    public void deleteUser(Long userId) {
        Boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("User with id" + userId + "does not exist");
        }
        userRepository.deleteById(userId);
    }




    }

