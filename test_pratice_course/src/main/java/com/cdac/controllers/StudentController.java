package com.cdac.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dtos.StudentDto;
import com.cdac.services.StudentService;

import io.swagger.v3.oas.models.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

	private final StudentService studentService;
	
	// add new student 
	// URL - http://host:port/students
	// success ----> 201, ApiResponse with message
	// failure ----> 400 - bad request and 409 - conflict 
	
	@PostMapping()
	public ResponseEntity<?> addNewStudent(@RequestBody @Valid StudentDto studentDto){
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(studentService.addNewStudent(studentDto));
	}
	
	// Fetch all students for given Course Name
    // - i/p course name
	// URL - http://host:port/students/{courseName}
	// success ----> 200, list of students
	// failure ----> 400, 404, ApiResponse with message
	
	@GetMapping("/{courseName}")
	public ResponseEntity<?> listAllStudentsByCourseName(@PathVariable String courseName) {
		
		return ResponseEntity.ok(studentService.listAllStudentsByCourseName(courseName));
	}
	
	//  Cancel Admission of specific student
	// input - student id
	// URL - http://host:port/students/{studentId}
	// success ----> 200, ApiResponse with message
	// failure ----> 404, ApiResponse with message
	
	@DeleteMapping("/{studentId}")
	public ResponseEntity<?> cancelStudentAdmission(@PathVariable Long studentId){
		
		return ResponseEntity.ok(studentService.cancelStudentAdmission(studentId));
	}
}
