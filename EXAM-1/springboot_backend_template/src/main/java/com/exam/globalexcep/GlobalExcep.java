package com.exam.globalexcep;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exam.dto.CustomResponse;
import com.exam.excep.CustomExcep;

@RestControllerAdvice
public class GlobalExcep {
	
	@ExceptionHandler(CustomExcep.class)
	public ResponseEntity<?> excepHandlder(CustomExcep e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse(e.getMessage(),"Failure"));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> excepHandlder(MethodArgumentNotValidException e){
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse(e.getMessage(),"Failure"));
		List<FieldError> fieldErrors = e.getFieldErrors();
		//al.stream().collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage));
		
		Map<String, String> collect = fieldErrors.stream().collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(collect);
	}

}
