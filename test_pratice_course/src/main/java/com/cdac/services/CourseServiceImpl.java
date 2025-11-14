package com.cdac.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.dtos.ApiResponse;
import com.cdac.dtos.CourseDto;
import com.cdac.dtos.UpdateCourseDetalisDto;
import com.cdac.exceptions.InvalidInputException;
import com.cdac.exceptions.ResourceAlreadyExistsException;
import com.cdac.exceptions.ResourceNotFoundException;
import com.cdac.models.Course;
import com.cdac.models.CourseCategory;
import com.cdac.repositories.CourseRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
	
	private final CourseRepository courseRepository;

	@Override
	public ApiResponse addNewCourse(CourseDto courseDto){
		
		// check if course with given course name already exists 
		if(courseRepository.existsByName(courseDto.getName())) {
			throw new ResourceAlreadyExistsException("Course with name " +courseDto.getName()+ " already exists!!!");
		}
		
		// validate that start date is before end date
		if(!validateStartAndEndDates(LocalDate.parse(courseDto.getStartDate()), LocalDate.parse(courseDto.getEndDate()))) {
			
			throw new InvalidInputException("Start Date can NOT be after End Date !!!!");
		}
		
		// convert dto to entity
		Course courseEntity = new Course();
		courseEntity.setName(courseDto.getName());
		courseEntity.setFees(courseDto.getFees());
		courseEntity.setMarksToPass(courseDto.getMarksToPass());
		courseEntity.setCategory(CourseCategory.valueOf(courseDto.getCategory()));
		courseEntity.setStartDate(LocalDate.parse(courseDto.getStartDate()));
		courseEntity.setEndDate(LocalDate.parse(courseDto.getEndDate()));
		
		// save new course
		Course persistCourse = courseRepository.save(courseEntity);
		
		return new ApiResponse("New Course added with id " +persistCourse.getId(), null);
	}

	// returns true if start date is before end date else returns false
	private boolean validateStartAndEndDates(LocalDate startDate, LocalDate endDate) {
		
		if(startDate.isBefore(endDate))
			return true;
		
		return false;
	}

	@Override
	public List<Course> listAllCoursesByCategory(CourseCategory category) {
	
		return courseRepository.findAllByCategory(category);
	}

	@Override
	public ApiResponse updateCourseDetails(UpdateCourseDetalisDto courseDto, Long courseId) {
		
		// check if course exists 
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course with id " +courseId+ " does not exists!!!!"));
		
				
		// validate that start date is before end date
		if(!validateStartAndEndDates(LocalDate.parse(courseDto.getStartDate()), LocalDate.parse(courseDto.getEndDate()))) {
					
					throw new InvalidInputException("Start Date can NOT be after End Date !!!!");
				}
		
		// convert dto to entity
		course.setFees(courseDto.getFees());
		course.setStartDate(LocalDate.parse(courseDto.getStartDate()));
		course.setEndDate(LocalDate.parse(courseDto.getEndDate()));
		
		return new ApiResponse("Course details updated", "Success");
	}

	@Override
	public ApiResponse cancelCourse(Long courseId) {
		
		// check if course exists 
		if(!courseRepository.existsById(courseId))
			throw new ResourceNotFoundException("Course with id " +courseId+ " does not exists!!!");
		
		// delete the course 
		courseRepository.deleteById(courseId);
		
		return new ApiResponse("Course cancelled....", "Success");
	}


}
