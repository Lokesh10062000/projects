package com.lokesh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lokesh.entity.Student;

@RestController
public class Data {
	
	@GetMapping("/data")
	public Student getStudent() {
		
		Student student = new Student(1,"lokesh","varma");
		
		return student;
	}

}
