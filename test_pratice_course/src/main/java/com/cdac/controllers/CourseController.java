package com.cdac.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dtos.CourseDto;
import com.cdac.dtos.UpdateCourseDetalisDto;
import com.cdac.models.Course;
import com.cdac.models.CourseCategory;
import com.cdac.services.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {

	private final CourseService courseService;
	
	// add new course
	// url - http://host:port/courses, method - post
	// success -----> status code - 201, ApiResponse with message
	// failure -----> 400, ApiResponse with message
	
	@PostMapping()
	public ResponseEntity<?> addNewCourse(@RequestBody @Valid CourseDto courseDto){
		
		return ResponseEntity.ok(courseService.addNewCourse(courseDto));
	}
	
	// Fetch all courses by given Category
	// URL - http://host:port/courses/{category}
	// success ----> 200, api response with message
	// failure ----> 400, api response with message
	
	@GetMapping("/{category}")
	public ResponseEntity<?> listAllCoursesByCategory(@PathVariable String category){
		
		List<Course> courses = courseService.listAllCoursesByCategory(CourseCategory.valueOf(category.toUpperCase()));
		
		if(courses.size() > 0)
			return ResponseEntity.ok(courses);
		
		return ResponseEntity.ok("There are no courses in specified category.....");
		
	}
	
	// update course details
	// URL -  http://host:port/courses/{courseId}/update
	// success -----> updated course details in JSON, 200
	// failure -----> 400, ApiResponse with message
	
	@PutMapping("/{courseId}/update")
	public ResponseEntity<?> updateCourseDetails(@RequestBody @Valid UpdateCourseDetalisDto courseDto, Long courseId) {
		
		return ResponseEntity.ok(courseService.updateCourseDetails(courseDto, courseId));
	}
	
	// cancel a course
	// input - course id
	// URL - http://host:port/courses/{courseId}
	// success - 200, ApiResponse with message
	// failure - 404, ApiResponse with message
	
	@DeleteMapping("/{courseId}")
	public ResponseEntity<?> cancelCourse(@PathVariable Long courseId){
		
		return ResponseEntity.ok(courseService.cancelCourse(courseId));
	}
}
