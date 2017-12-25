package by.htp.library.service.exception;

/**
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public class ServiceExeptionLoginExist extends Exception{
	private static final long serialVersionUID = 1L;
	public ServiceExeptionLoginExist(){super();}

	public ServiceExeptionLoginExist(String mes)
	{super(mes);}

	public ServiceExeptionLoginExist(Exception e)
	{super(e);}

	public ServiceExeptionLoginExist(String mes, Exception e)
	{super(mes, e);}

}


