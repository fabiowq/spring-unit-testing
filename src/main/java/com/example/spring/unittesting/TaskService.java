package com.example.spring.unittesting;

import java.util.List;
import java.util.Optional;

public interface TaskService {

	List<Task> findAll();
	List<Task> findAllByUser(long userId);
	Optional<Task> findById(long taskId);
	
}
