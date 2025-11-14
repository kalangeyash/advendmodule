package com.exam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.exam.entities.Category;
import com.exam.entities.Course;


public interface CourseRepo extends JpaRepository<Course, Long>{

	
	Optional<Course> findById(Long id);
	
	List<Course> findByCate(Category cate);

//	List<Course> findByCat(Category name); 
	
	@Modifying
	@Query("update Course c set c.name=?1 where c.id=?2") int Update(String name,Long id);
}
