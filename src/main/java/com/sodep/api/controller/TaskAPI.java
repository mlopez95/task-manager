package com.sodep.api.controller;

import com.sodep.api.beans.TaskRequest;
import com.sodep.api.exception.ApiException;
import com.sodep.entities.Task;
import com.sodep.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author mlopez
 * @fecha 2019-05-11,09:07
 */

@RestController
@RequestMapping("/api")
public class TaskAPI {

    @Autowired
    private TaskService taskService;

    @GetMapping("/task")
    public Iterable<Task> getAllTask() throws ApiException {
        return taskService.findAll();
    }

    @GetMapping("/tasks/{assignee_id}")
    public List<Task> getAllTaskForAssignee(
            @PathVariable("assignee_id") Long id) throws ApiException{
        return taskService.findAllForAssignee(id);
    }

    @GetMapping("/task/filter")
    public Iterable<Task> getAllTaskForFilter (
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam("completed") boolean completed) throws ApiException{
        return taskService.findAllForFilter(completed, page, size);
    }

    @GetMapping(value = "/task/{id}")
    public Task getTaskById(
            @PathVariable(value = "id") Long id) throws ApiException{
        return taskService.findById(id);
    }

    @PutMapping("/task/{id}")
    public Task updateTask(
            @PathVariable("id") Long id,
            @Valid @RequestBody TaskRequest request) throws ApiException{
        return taskService.updateTask(id,request);
    }

    @DeleteMapping("/task/{id}")
    public void deleteTask(
            @PathVariable("id") Long id) throws ApiException{
        taskService.deleteTask(id);
    }

    @PostMapping("/task")
    public Task createTask(
            @Valid @RequestBody TaskRequest request) throws ApiException{
        return taskService.saveTask(request);
    }
}
