package com.yorbit.moviebooking.exception;

public class ScreenNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ScreenNotFoundException(String message) {
		super(message);
    }
}
