package com.example.spring.unittesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

	private final static List<Task> TASKS = buildTaskList();
	
	private static List<Task> buildTaskList() {
		List<Task> l = new ArrayList<>();
		l.add(new Task(1, "Implement unit testing", 10));
		l.add(new Task(2, "Push on git", 20));
		return l;
	}
	
	@Override
	public List<Task> findAll() {
		return TASKS;
	}
	
	@Override
	public List<Task> findAllByUser(long userId) {
		return TASKS.stream().filter(t -> t.getUserId() == userId).collect(Collectors.toList());
	}
	
	@Override
	public Optional<Task> findById(long taskId) {
		return TASKS.stream().filter(t -> t.getId() == taskId).findFirst();
	}
	
}
