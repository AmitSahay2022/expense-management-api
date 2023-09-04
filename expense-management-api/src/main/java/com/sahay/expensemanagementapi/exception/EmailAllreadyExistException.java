package com.sahay.expensemanagementapi.exception;

public class EmailAllreadyExistException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailAllreadyExistException(String message) {
		super(message);
	}
}
