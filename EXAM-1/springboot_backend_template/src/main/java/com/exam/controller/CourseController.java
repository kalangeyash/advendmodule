package com.exam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.dto.CourseDTO;
import com.exam.dto.CourseDTO2;
import com.exam.entities.Category;
import com.exam.service.CourseService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {
	
	private final CourseService cervice;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerCourse(@RequestBody @Valid CourseDTO dtc){
		return ResponseEntity.ok(cervice.addCourse(dtc));
	}
	
	
	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateCourse(@RequestBody @Valid CourseDTO2 ct,Long id)
	{
		return ResponseEntity.ok(cervice.updateCourse(ct,id));
	}
	
	@GetMapping("/find/{name}")
	public ResponseEntity<?> showAll(@PathVariable Category name){
		return ResponseEntity.ok(cervice.showAll(name));
	}
	
	

}
