package by.htp.library.service.impl;

import org.junit.Assert;
import org.junit.Test;
import by.htp.library.bean.Book;
import by.htp.library.service.LibraryService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.exception.ServiceExceptionValid;
import by.htp.library.service.factory.ServiceFactory;

/**
 * Test class for LibraryService
 * 
 * @author Mashkouski Andrei
 * @version 1.0
 */
public class TestLibraryService {

	private ServiceFactory factory = ServiceFactory.getInstance();
	private LibraryService libraryService = factory.getLibraryService();

	@Test
	public void testValidBook() throws ServiceException, ServiceExceptionValid {

		try {
			libraryService.saveBook(new Book(0L, "", "", "", "", 0, "", ""));

		} catch (ServiceExceptionValid e) {
			Assert.assertEquals("Incorrect input data", e.getMessage());

		}
	}

	@Test(expected = ServiceExceptionValid.class)
	public void testNullBook() throws ServiceException, ServiceExceptionValid {
		libraryService.saveBook(new Book(0L, null, null, null, null, 0, null, null));

	}

	@Test(expected = NullPointerException.class)
	public void testSearch() throws ServiceException {
		libraryService.search("Simmons", "author");
	}
}
