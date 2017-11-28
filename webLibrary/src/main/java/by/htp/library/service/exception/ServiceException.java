package by.htp.library.service.exception;

/**
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public class ServiceException extends Exception{
	private static final long serialVersionUID = 1L;
	public ServiceException(){super();}

	public ServiceException(String mes)
	{super(mes);}

	public ServiceException(Exception e)
	{super(e);}

	public ServiceException(String mes, Exception e)
	{super(mes, e);}

}
