package org.smartsupply.model.exception;

public class RmsUnexpectedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2350440537721966581L;

	/**
     * 
     */
	public RmsUnexpectedException() {
		super();
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public RmsUnexpectedException(String message, Throwable arg1) {
		super(message, arg1);
	}

	/**
	 * @param arg0
	 */
	public RmsUnexpectedException(String message) {
		super(message);

	}

	/**
	 * @param arg0
	 */
	public RmsUnexpectedException(Throwable arg0) {
		super(arg0);
	}

}
