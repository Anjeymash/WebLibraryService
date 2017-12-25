package by.htp.library.service.exception;


	/**
	 * @author Mashkouski Andrei
	 * @version 1.0 
	 */
	public class ServiceExeptionEmailExist extends Exception{
		private static final long serialVersionUID = 1L;
		public ServiceExeptionEmailExist(){super();}

		public ServiceExeptionEmailExist(String mes)
		{super(mes);}

		public ServiceExeptionEmailExist(Exception e)
		{super(e);}

		public ServiceExeptionEmailExist(String mes, Exception e)
		{super(mes, e);}

	}


