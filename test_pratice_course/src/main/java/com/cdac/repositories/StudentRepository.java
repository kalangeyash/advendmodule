package com.cdac.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {

	boolean existsByEmail(String email);

	List<Student> findAllByCourseName(String courseName);

}
