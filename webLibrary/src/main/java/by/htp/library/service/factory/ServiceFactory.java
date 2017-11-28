package by.htp.library.service.factory;

import by.htp.library.service.ClientService;
import by.htp.library.service.LibraryService;
import by.htp.library.service.RentService;
import by.htp.library.service.impl.ClientServiceImpl;
import by.htp.library.service.impl.LibraryServiceImpl;
import by.htp.library.service.impl.RentServiceImpl;
/**
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	private final LibraryService libraryService = new LibraryServiceImpl();
	private final ClientService clientService = new ClientServiceImpl();
	private final RentService rentService = new RentServiceImpl();

	public static ServiceFactory getInstance() {
		return instance;
	}

	public LibraryService getLibraryService() {
		return libraryService;
	}
	public ClientService getClientService() {
		return clientService;
	}
	public RentService getRentService() {
		return rentService;
	}
}
