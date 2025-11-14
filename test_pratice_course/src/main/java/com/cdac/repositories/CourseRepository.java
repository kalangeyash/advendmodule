package com.cdac.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.models.Course;
import com.cdac.models.CourseCategory;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	boolean existsByName(String name);
	

	Optional<Course> findByName(String name);


	List<Course> findAllByCategory(CourseCategory category);
}
