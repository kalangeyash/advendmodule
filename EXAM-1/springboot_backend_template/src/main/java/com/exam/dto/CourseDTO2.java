package com.exam.dto;

import java.time.LocalDate;

import com.exam.entities.Category;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CourseDTO2 {
	@NotNull(message = "no")
	private LocalDate st;
	private LocalDate et;
	private double fee;
}
