package com.example.TaskMaster.Controller;


import com.example.TaskMaster.Model.User;
import com.example.TaskMaster.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getUsers();

    }

    @GetMapping(path = "{userId}")
    public User getUser(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }
    @PutMapping(path = "role/{userId}")
    public void updateRole(@PathVariable("userId") Long userId, @RequestParam String role) {
        userService.updateRole(userId,role);

    }

    @PutMapping(path = "password/{userId}")
    public void updatePassword(@PathVariable("userId") Long userId, @RequestParam String password) {
        userService.updatePassword(userId, password);
    }


    @PutMapping(path = "username/{userId}")
    public void updateUsername(@PathVariable("userId") Long userId, @RequestParam String username) {
        userService.updateUsername(userId,username);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable ("userId") Long userId) {
        userService.deleteUser(userId);
    }

//    @PostMapping("/assign-task")
//    public ResponseEntity<String> assignTaskToUser(@RequestParam Long taskId, @RequestParam Long userId) {
//        userService.assignTaskToUser(taskId, userId);
//        return ResponseEntity.ok("Task assigned successfully");
//    }


}
