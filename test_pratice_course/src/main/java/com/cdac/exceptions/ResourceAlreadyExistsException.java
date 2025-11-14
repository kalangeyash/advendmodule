package com.cdac.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {
	public ResourceAlreadyExistsException(String errMesg) {
		super(errMesg);
	}
}
