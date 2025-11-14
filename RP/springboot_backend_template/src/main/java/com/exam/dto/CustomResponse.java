package com.exam.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomResponse {
	String message;
	String status;
	public CustomResponse(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}
	
}
