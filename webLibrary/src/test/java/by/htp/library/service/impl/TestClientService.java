package by.htp.library.service.impl;

import org.junit.Assert;
import org.junit.Test;
import by.htp.library.bean.User;
import by.htp.library.service.ClientService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;

public class TestClientService {
	private ServiceFactory factory = ServiceFactory.getInstance();
	private ClientService clientService = factory.getClientService();

//	@Test
	public void testValidUser() throws ServiceException {

		try {
			clientService.saveUser(new User(0L, "", "", "", "", "", "", "", ""));

		} catch (ServiceException e) {
			Assert.assertEquals("Incorrect input data", e.getMessage());

		}
	}

//	@Test(expected = ServiceException.class)
	public void testNullUser() throws ServiceException {
		clientService.saveUser(new User(0L, null, null, null, null, null, null, null, null));

	}

	// ConnectionPool is not initialized
//	@Test(expected = NullPointerException.class)
	public void testSignIn() throws ServiceException {
		clientService.singIn("Anjey", "123");
	}
}
