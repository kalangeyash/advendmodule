package com.exam.exceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exam.dto.CustomResponse;
import com.exam.exception.CustomException;

@RestControllerAdvice
public class GlobalExceptionhandler {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> handleCustomException(CustomException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse(e.getMessage(),"failure"));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleCustomException(MethodArgumentNotValidException e) {
		
		List<FieldError> al = e.getFieldErrors();
		Map<String,String> mp = al.stream().collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mp);
	}
}
