package by.htp.library.service.impl;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import by.htp.library.bean.Rent;
import by.htp.library.service.RentService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.exception.ServiceExceptionValid;
import by.htp.library.service.factory.ServiceFactory;

/**
 * Test class for RentService
 * 
 * @author Mashkouski Andrei
 * @version 1.0
 */
public class TestRentService {

	private ServiceFactory factory = ServiceFactory.getInstance();
	private RentService rentService = factory.getRentService();

	@Test
	public void testValidRent() throws ServiceException, ServiceExceptionValid {

		try {
			rentService.saveRent(new Rent(0L, 0L, 0L, null, null, 0));
		} catch (ServiceExceptionValid e) {
			Assert.assertEquals("Incorrect input data", e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	@Test(expected = ServiceExceptionValid.class)
	public void testDate() throws ServiceException, ServiceExceptionValid {
		rentService.saveRent(new Rent(100L, 1L, 1L, new Date(2001, 01, 01), new Date(2000, 01, 01), 0));

	}

}
