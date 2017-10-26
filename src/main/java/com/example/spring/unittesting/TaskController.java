package com.example.spring.unittesting;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskService service;
	
	public TaskController(@Autowired TaskService service) {
		this.service = service;
	}

    @GetMapping(value = "")
    public List<Task> all() {
        List<Task> tasks = service.findAll();
        return tasks;
    }
	
    @GetMapping(value = "/{taskId}")
    public Task byId(@PathVariable(name = "taskId", required = true) long taskId) {
        Optional<Task> task = service.findById(taskId);
        if (!task.isPresent()) {
        	throw new ResourceNotFoundException();
        }
        return task.get();
    }
    
    @GetMapping(value = "/byUser/{userId}")
    public List<Task> allByUser(@PathVariable(name = "userId", required = true) long userId) {	
        List<Task> tasks = service.findAllByUser(userId);
        return tasks;
    }
	
}
