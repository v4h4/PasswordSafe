package org.luan;
public class LUANException extends Exception {
	private static final long serialVersionUID = 0;
	private Throwable cause;

    /**
     * Constructs a LUANException with an explanatory message.
     * @param message Detail about the reason for the exception.
     */
    public LUANException(String message) {
        super(message);
    }

    public LUANException(Throwable cause) {
        super(cause.getMessage());
        this.cause = cause;
    }

    public Throwable getCause() {
        return this.cause;
    }
}