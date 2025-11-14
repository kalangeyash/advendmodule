package com.cdac.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {

	@NotBlank
	private String name;
	private String category;
	
	@NotBlank
	private String startDate;
	
	@NotBlank
	private String endDate;
	
	@NotNull
	@Min(1) // to ensure positive value
	private Double fees;
	
	@NotNull
	@Min(1)
	private Integer marksToPass;
}
