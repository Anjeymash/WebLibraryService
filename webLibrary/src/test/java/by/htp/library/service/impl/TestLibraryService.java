package by.htp.library.service.impl;

import org.junit.Assert;
import org.junit.Test;
import by.htp.library.bean.Book;
import by.htp.library.service.LibraryService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;

public class TestLibraryService {
	
	
		private ServiceFactory factory = ServiceFactory.getInstance();
		private LibraryService libraryService = factory.getLibraryService();

	//	@Test
		public void testValidUser() throws ServiceException {

			try {
				libraryService.saveBook(new Book(0L, "", "", "", "", 0, "", ""));
			
			} catch (ServiceException e) {
				Assert.assertEquals("Incorrect input data", e.getMessage());

			}
		}

	//	@Test(expected = ServiceException.class)
		public void testNullBook() throws ServiceException {
			libraryService.saveBook(new Book(0L, null, null, null, null, 0, null, null));

		}
		
//	@Test(expected = NullPointerException.class)
		public void testSearch() throws ServiceException {
			libraryService.search("Simmons", "author");
		}
	}



