package com.zensar.exceptions;

public class NoSuchProductExistsException extends RuntimeException{
	private String message;

	public NoSuchProductExistsException() {
		super();
	}

	public NoSuchProductExistsException(String message) {
		super(message);
		this.message = message;
	}
	
	

}
