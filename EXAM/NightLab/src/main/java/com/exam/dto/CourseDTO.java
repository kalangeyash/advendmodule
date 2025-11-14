package com.exam.dto;

import java.time.LocalDate;

import com.exam.entities.Category;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CourseDTO {
	
private String name;
	
	private Category cat;
	
	private LocalDate start_date;
	
	
	private LocalDate end_date;
	
	
	private double fees;
	
	
	private double marks;

}
