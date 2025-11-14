package com.cdac.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.dtos.ApiResponse;
import com.cdac.dtos.CourseDto;
import com.cdac.dtos.UpdateCourseDetalisDto;
import com.cdac.models.Course;
import com.cdac.models.CourseCategory;

@Service
@Transactional
public interface CourseService {

	ApiResponse addNewCourse(CourseDto courseDto);

	List<Course> listAllCoursesByCategory(CourseCategory valueOf);

	ApiResponse updateCourseDetails(UpdateCourseDetalisDto courseDto, Long courseId);

	ApiResponse cancelCourse(Long courseId);
	
}
