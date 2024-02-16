package com.example.TaskMaster.Controller;

import com.example.TaskMaster.Model.Task;
import com.example.TaskMaster.Service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/task")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public void addTask(@RequestBody Task task){
        taskService.addTask(task);
    }

    @GetMapping()
    public List<Task> getAllTask(){
        return taskService.getTasks();

    }

      @PostMapping("/assign-task/{userId}/tasks/{taskId}")
      public ResponseEntity<String> assignTaskToUser(@PathVariable Long taskId, @PathVariable Long userId) {
        taskService.assignTasktoUser(taskId, userId);
        return ResponseEntity.ok("Task assigned successfully");
    }
     @DeleteMapping("/rmvTaskFrmUsr/{userId}/tasks/{taskId}")
     public ResponseEntity<String> removeTaskfromUser(@PathVariable Long taskId, @PathVariable Long userId){
        taskService.removeTaskFromUser(taskId,userId);
        return ResponseEntity.ok("Task removed from user successfully");
     }

    @GetMapping("/getTasksOfUser/{userId}")
    public List<Task> getAllTasksOfUser(@PathVariable Long userId) {
        return taskService.getAllTasksOfUser(userId);
    }



}
