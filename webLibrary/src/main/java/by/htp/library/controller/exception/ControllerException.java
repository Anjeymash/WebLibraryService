package by.htp.library.controller.exception;
/**
 * Controller exception class
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public class ControllerException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ControllerException() {
		super();
	}

	public ControllerException(String message) {
		super(message);
	}

	public ControllerException(Exception e) {
		super(e);
	}

	public ControllerException(String message, Exception e) {
		super(message, e);

	}

}