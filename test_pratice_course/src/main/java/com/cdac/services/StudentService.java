package com.cdac.services;

import java.util.List;

import com.cdac.dtos.ApiResponse;
import com.cdac.dtos.StudentDto;
import com.cdac.models.Student;

public interface StudentService {

	ApiResponse addNewStudent(StudentDto studentDto);

	List<Student> listAllStudentsByCourseName(String courseName);

	ApiResponse cancelStudentAdmission(Long studentId);

}
