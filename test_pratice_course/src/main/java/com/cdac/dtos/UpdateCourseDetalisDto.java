package com.cdac.dtos;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCourseDetalisDto {
	
	private String startDate;
	
	private String endDate;

	@Min(1) // to ensure positive value
	private Double fees;
}
