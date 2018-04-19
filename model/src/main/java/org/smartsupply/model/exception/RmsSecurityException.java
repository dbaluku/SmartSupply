package org.smartsupply.model.exception;
/**
 * Class to handle Spring Security exceptions
 * Basically to wrap and serialise them to the client
 *
 * @author Job
 *
 */
public class RmsSecurityException extends ValidationException {

private static final long serialVersionUID = 792037043275797398L;
	
	public RmsSecurityException() {}

	/**
	 * @param throwable
	 * instance any unregistered exception that might be thrown on the server
	 */
	public RmsSecurityException(Throwable throwable) {
		super(throwable);
	}
	
	public RmsSecurityException(String message){
		super(message);
	}
}
	