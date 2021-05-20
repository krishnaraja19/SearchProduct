package com.telenor.product.exception;

public class ResourceNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3406904303424460133L;

	public ResourceNotFoundException(String exception) {
		super(exception);
	}
}
