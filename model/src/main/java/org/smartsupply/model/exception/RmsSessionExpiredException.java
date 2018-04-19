package org.smartsupply.model.exception;

/**
 * Exception that is thrown when the user's session has expired. Indicates that
 * the user should be redirected to a login page
 * 
 * @author Job
 * 
 */
public class RmsSessionExpiredException extends Exception {

	private static final long serialVersionUID = -4151708298744270279L;

	public RmsSessionExpiredException() {
		super();
	}

	public RmsSessionExpiredException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 */
	public RmsSessionExpiredException(String arg0) {
		super(arg0);

	}

	public RmsSessionExpiredException(Throwable throwable) {
		super(throwable);
	}
}
