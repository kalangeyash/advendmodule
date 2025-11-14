package com.cdac;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cdac.dtos.ApiResponse;
import com.cdac.exceptions.InvalidInputException;
import com.cdac.exceptions.ResourceAlreadyExistsException;
import com.cdac.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// handles exception related to already existing resources, status code 409
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<?> handleResourceAlreadyExistsException(ResourceAlreadyExistsException r){
		
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ApiResponse(r.getMessage(), "Falied"));
	}
	
	// status code - 404
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlResourceNotFoundException(ResourceNotFoundException r){
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(new ApiResponse(r.getMessage(), "Falied"));
	}
	
	// Handle invalid input (400 Bad Request)
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ApiResponse> handleInvalidInput(InvalidInputException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(ex.getMessage(), "Failed"));
    }
    
    // handle IllegalArgumentException 
    @ExceptionHandler(IllegalArgumentException .class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException  e){
    	
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    			.body(new ApiResponse("Invalid input: " + e.getMessage(), "Failed"));
    }
    
	// handles exception related to Validations
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> hanldeMethodArgumentNotValidException(MethodArgumentNotValidException m){
		
		// get list of rejected fields
		List<FieldError> fieldErrors = m.getFieldErrors();
		
		// convert it to map
		Map<String, String> errorsMap = fieldErrors.stream()
				.collect(Collectors.toMap(f -> f.getField(),f -> f.getDefaultMessage()));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(errorsMap);
		
	}	
	
	// catches all exceptions
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntineException(RuntimeException r){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiResponse(r.getMessage(), "Falied"));
		
	}
}
