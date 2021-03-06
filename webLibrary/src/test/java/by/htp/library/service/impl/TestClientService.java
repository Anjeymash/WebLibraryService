package by.htp.library.service.impl;

import org.junit.Assert;
import org.junit.Test;
import by.htp.library.bean.User;
import by.htp.library.service.ClientService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.exception.ServiceExceptionValid;
import by.htp.library.service.exception.ServiceExeptionEmailExist;
import by.htp.library.service.exception.ServiceExeptionLoginExist;
import by.htp.library.service.factory.ServiceFactory;

/**
 * Test class for ClientService
 * 
 * @author Mashkouski Andrei
 * @version 1.0
 */
public class TestClientService {
	private ServiceFactory factory = ServiceFactory.getInstance();
	private ClientService clientService = factory.getClientService();

	@Test
	public void testValidUser() throws ServiceExceptionValid, ServiceException, ServiceExeptionEmailExist, ServiceExeptionLoginExist {

		try {
			clientService.saveUser(new User(0L, "", "", "", "", "", "", "", ""));

		} catch (ServiceExceptionValid e) {
			Assert.assertEquals("Incorrect input data", e.getMessage());

		}
	}

	@Test(expected = ServiceExceptionValid.class)
	public void testNullUser() throws ServiceExceptionValid, ServiceException, ServiceExeptionEmailExist, ServiceExeptionLoginExist {
		clientService.saveUser(new User(0L, null, null, null, null, null, null, null, null));

	}

	// ConnectionPool is not initialized
	@Test(expected = NullPointerException.class)
	public void testSignIn() throws ServiceException, ServiceExceptionValid {
		clientService.singIn("Anjey", "123");
	}
}
