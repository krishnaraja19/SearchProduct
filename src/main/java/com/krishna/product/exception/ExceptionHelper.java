package com.krishna.product.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.krishna.product.model.ErrorDetails;

@ControllerAdvice
public class ExceptionHelper {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHelper.class);
	
	    @ExceptionHandler( ResourceNotFoundException.class)
	    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex,WebRequest request) {
	    	ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
	    	logger.error("Invalid  Input Exception: ",ex.getMessage());
	        return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND); 
	    }
	    
	   
	    @ExceptionHandler(value = { Exception.class })
	    public ResponseEntity<?> handleGlobalException(Exception ex,WebRequest request) {
	    	ErrorDetails errorDetails = new ErrorDetails(new Date(),"Internal API Error",request.getDescription(false));
	    	logger.error("Exception : ",ex.getMessage());
	        return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
