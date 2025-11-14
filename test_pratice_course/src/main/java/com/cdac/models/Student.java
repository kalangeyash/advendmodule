package com.cdac.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor

@Entity
@Table(name = "students")
@AttributeOverride(name = "id", column = @Column(name = "student_id"))
public class Student extends BaseModel{
	
//	student name (Cannot be Blank)
//	3.	Email (Cannot be Blank)
//	4.	Password (Cannot be Blank)
//	5.     Marks
//	6.	Course id - Foreign key
	
	@Column(length = 30)
	private String name;
	
	@Column(length = 30)
	private String email;
	
	@Column(length = 30)
	private String password;
	
	private int marks;
	
	@JoinColumn(name = "course_id")
	@ManyToOne
	private Course course;
	
	/*
	 * Student   Course
	 *   1         1
	 *   m         1
	 *   m:1
	 * */
}
