package com.epam.kata.csvservice.exceptions;

public class FilePathNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FilePathNotFoundException(String message) {
        super(message);
    }
}
