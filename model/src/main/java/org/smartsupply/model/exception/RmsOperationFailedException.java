package org.smartsupply.model.exception;

/**
 * Represents an exception thrown after a validation. This class extends the
 * Exception class.
 * 
 * @author ctumwebaze
 * 
 */
public class RmsOperationFailedException extends Exception {

	/**
     * 
     */
	private static final long serialVersionUID = 2832559624459402988L;

	/**
     * 
     */
	public RmsOperationFailedException() {
		super();
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public RmsOperationFailedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 */
	public RmsOperationFailedException(String arg0) {
		super(arg0);

	}

	/**
	 * @param arg0
	 */
	public RmsOperationFailedException(Throwable arg0) {
		super(arg0);
	}

}
