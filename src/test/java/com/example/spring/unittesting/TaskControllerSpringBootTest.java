/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.spring.unittesting;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.spring.unittesting.Task;
import com.example.spring.unittesting.TaskService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerSpringBootTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TaskService service;
	
	@Before
	public void beforeTests() throws Exception {

	}

	@Test
	public void findAll() throws Exception {
		final List<Task> tasks = Arrays.asList(
			new Task(1, "Code Junit", 10),
	 		new Task(2, "Execute Junit", 20),
	 		new Task(3, "Close Junit", 20)
		); 
		when(service.findAll()).thenReturn(tasks);
		mockMvc.perform(get("/tasks")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(tasks.size())));
	}
	
	@Test
	public void findByIdOK() throws Exception {
		// Type must be int to works, with long it fails: 'JSON path "$.id" Expected: is <1L> but: was <1>'
		final int taskId = 1; 
		when(service.findById(taskId))
	 		.thenReturn(
	 			Optional.of(new Task(taskId, "Code Junit", 10))
	 		);		
		mockMvc.perform(get("/tasks/" + taskId)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(taskId)));
	}
	
	@Test
	public void findByIdNotFound() throws Exception {
		final long taskId = 4; 
		when(service.findById(taskId))
	 		.thenReturn(
	 			Optional.empty()
	 		);		
		mockMvc.perform(get("/tasks/" + taskId)).andDo(print()).andExpect(status().isNotFound());
	}

}