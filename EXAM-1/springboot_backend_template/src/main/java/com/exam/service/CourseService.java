package com.exam.service;

import java.util.List;

import com.exam.dto.CourseDTO;
import com.exam.dto.CourseDTO2;
import com.exam.dto.CustomResponse;
import com.exam.entities.Category;

public interface CourseService {

	CustomResponse addCourse(CourseDTO dtc);

	String updateCourse(CourseDTO2 ct, Long id);

	List<CourseDTO>showAll(Category name);

}
