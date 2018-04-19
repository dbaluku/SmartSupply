package org.smartsupply.api.exception;

public class DatabaseUpdateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4911688060253284950L;

	public DatabaseUpdateException() {

	}

	public DatabaseUpdateException(String message) {
		super(message);
	}

	public DatabaseUpdateException(Throwable cause) {
		super(cause);
	}

	public DatabaseUpdateException(String message, Throwable cause) {
		super(message, cause);
	}

}
