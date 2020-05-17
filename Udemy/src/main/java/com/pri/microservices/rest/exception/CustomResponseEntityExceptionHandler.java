package com.pri.microservices.rest.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
//class of common exception handling in spring. so extending it and implementing all methods of it.
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pri.microservices.rest.user.UserNotFoundException;

@RestController //because its providing the response back so have to mark it with __ in case of exception.
@ControllerAdvice //to make it applicable across all controllers / share across multiple classes. 
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		 
		return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(Exception ex, WebRequest request){
		
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		 
		return new ResponseEntity(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotCreatedExceptions(Exception ex, WebRequest request){
		
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		 
		return new ResponseEntity(response,HttpStatus.SERVICE_UNAVAILABLE);
	}
}
