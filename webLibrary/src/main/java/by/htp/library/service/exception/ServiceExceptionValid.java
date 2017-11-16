package by.htp.library.service.exception;

public class ServiceExceptionValid extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceExceptionValid() {
		super();
	}

	public ServiceExceptionValid(String mes) {
		super(mes);
	}

	public ServiceExceptionValid(Exception e) {
		super(e);
	}

	public ServiceExceptionValid(String mes, Exception e) {
		super(mes, e);
	}

}