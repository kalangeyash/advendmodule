package com.cdac.models;

import java.time.LocalDate;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AttributeOverride(name = "id", column = @Column(name = "course_id"))
public class Course extends BaseModel {

//	Name (unique , Cannot be Blank, Example : Java SE,Web Programming in Java ,
//			Spring Boot , Spring Cloud, Oracle DB , MySql DB  )
//			3.	Category (Enum, Example: JAVA,DBT)
//			4.	Start Date (Cannot be Blank)
//			5.	End Date (Cannot be Blank)
//			6.	Fees (Cannot be Blank)
//			7.	Marks to Pass (Cannot be Blank)
//	
	
	@Column(length = 50, unique = true)
	private String name;
	
	@Enumerated(EnumType.STRING)
	private CourseCategory category;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private double fees;
	
	private int marksToPass;
	
}
