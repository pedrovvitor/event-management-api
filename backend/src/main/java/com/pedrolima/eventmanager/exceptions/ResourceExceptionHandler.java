package com.pedrolima.eventmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<GenericErrorResponse> businessExceptionHandler(BusinessException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericErrorResponse(e.getMessage()));
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<GenericErrorResponse> resourceNotFoundHandler(ResourceNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericErrorResponse(e.getMessage()));
	}
}
