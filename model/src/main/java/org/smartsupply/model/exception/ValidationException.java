package org.smartsupply.model.exception;

public class ValidationException extends Exception {

    private static final long serialVersionUID = -7415205973383449312L;

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Throwable throwable) {
        super(throwable);
    }

    public ValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }

}