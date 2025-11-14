package com.exam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.dto.StudentDTO;
import com.exam.service.StudentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {
	
	private final StudentService service;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerStudent(@RequestBody StudentDTO std){
		return ResponseEntity.ok(service.registerStudent(std));
	}
	

	

	

}
