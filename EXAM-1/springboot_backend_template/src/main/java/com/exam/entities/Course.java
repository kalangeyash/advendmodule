package com.exam.entities;

import java.time.LocalDate;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Course {
	@Column(name="Course_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,unique = true)
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Category cate;
	
	@Column(nullable = false)
	private LocalDate start_date;
	
	
	@Column(nullable = false)
	private LocalDate end_date;
	
	@Column(nullable = false)
	private double fees;
	
	@Column(name= "passingmarks",nullable = false)
	private double marks;

}
