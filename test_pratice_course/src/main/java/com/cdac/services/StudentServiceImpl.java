package com.cdac.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.dtos.ApiResponse;
import com.cdac.dtos.StudentDto;
import com.cdac.exceptions.AdmissionEligibilityCriteriaException;
import com.cdac.exceptions.ResourceAlreadyExistsException;
import com.cdac.exceptions.ResourceNotFoundException;
import com.cdac.models.Course;
import com.cdac.models.Student;
import com.cdac.repositories.CourseRepository;
import com.cdac.repositories.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
	
	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;

	@Override
	public ApiResponse addNewStudent(StudentDto studentDto) {
		
		// check for duplicate email 
		if(studentRepository.existsByEmail(studentDto.getEmail())) {
			throw new ResourceAlreadyExistsException("Student with email " +studentDto.getEmail()+ " already exists!!!");
		}
		
		// get course object with specified name
				Course course = courseRepository.findByName(studentDto.getCourse())
						.orElseThrow(() -> new ResourceNotFoundException("Course with name " +studentDto.getCourse()+ " does not exists!!!"));
					
		// validate if student have min marks to take admission in specified course
		if(!validateMinMarks(course, studentDto.getMarks())) {
			throw new AdmissionEligibilityCriteriaException("Not eligible for the course - low marks");
		}
		
		// convert student dto to Student entity
		Student student = new Student();
		student.setEmail(studentDto.getEmail());
		student.setName(studentDto.getName());
		student.setPassword(studentDto.getPassword());
		student.setMarks(studentDto.getMarks());
		student.setCourse(course);
		
		// save the student after validations
		Student persistStudent = studentRepository.save(student);
		
		return new ApiResponse("New Student added with id " +persistStudent.getId(), "Success");
	}

	// returns true if student's marks is less than required min marks to get admission else returns false
	private boolean validateMinMarks(Course course, int studentMarks) {
		
		if(studentMarks < course.getMarksToPass())
			return false;
		
		return true;
	}

	@Override
	public List<Student> listAllStudentsByCourseName(String courseName) {
		
		// check if course with given name exists
		if(!courseRepository.existsByName(courseName))
			throw new ResourceNotFoundException("Course with name " +courseName+ " does not exists!!!");
		
		// return the list
		return studentRepository.findAllByCourseName(courseName);
	}

	@Override
	public ApiResponse cancelStudentAdmission(Long studentId) {
		
		// check if student exists with given id 
		if(!studentRepository.existsById(studentId))
			throw new ResourceNotFoundException("Student with id " +studentId+ " does not exists!!!!");
		
		studentRepository.deleteById(studentId);
		
		return new ApiResponse("Student's admission cancelled!!!", "Success");
	}

}
