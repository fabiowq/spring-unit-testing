package com.example.spring.unittesting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Task {

	private long id;
	private String name;
	private long userId;
	
}
