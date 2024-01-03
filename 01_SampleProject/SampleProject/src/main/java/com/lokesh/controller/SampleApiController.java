package com.lokesh.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1")
public class SampleApiController {
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello Lokesh";
	}
	
	

}
