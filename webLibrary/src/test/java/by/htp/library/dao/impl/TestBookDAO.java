package by.htp.library.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import by.htp.library.bean.Book;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.connection.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.factory.DAOFactory;

public class TestBookDAO {
	private DAOFactory factory = DAOFactory.getInstance();
	private BookDAO bookDAO = factory.getBookDAO();
	private static final Book DEFAULT_BOOK = new Book(2L, "Angel", "Sidney", "children", "2013", 9, "1", "Context");
	private static final Book TEST_BOOK = new Book(1L, "TestBook", "TestBook", "TestBook", "2017", 1, "1", "TestContext");

	@Before
	public void init() throws ServletException, ConnectionPoolException {
		ConnectionPool conPool = ConnectionPool.getInstance();
		conPool.initPoolData();
	}
		
	@After
	public void destroy() {
		ConnectionPool conPool = ConnectionPool.getInstance();
		conPool.dispose();
	}
	@Test
	public void testListBook() throws DAOException {
		List<Book> listBook= new ArrayList<>();
		bookDAO.saveBook(TEST_BOOK);
		Assert.assertNotNull(listBook);
	}
	@Test
	public void testBookById() throws DAOException {
		Book book = bookDAO.bookById(2L);
		Assert.assertEquals(2L, book.getId());
	}
	@Test
	public void testSaveBook() throws DAOException {
		Long id = bookDAO.saveBook(TEST_BOOK);
		Book book = bookDAO.bookById(id);
		Assert.assertEquals(TEST_BOOK.getTitle(), book.getTitle());
	}
	
	@Test
	public void testUpdateBook() throws DAOException {
		Book updateBook = DEFAULT_BOOK;
		 updateBook.setYear("2018");
		Long id = bookDAO.updateBook(updateBook);
		Book book = bookDAO.bookById(id);
		Assert.assertEquals("2018", book.getYear());
	}
	
	@Test
	public void testSearchBook() throws DAOException {
		List<Book> listBook= new ArrayList<>();
		listBook = bookDAO.search("Angel", "title");
		Assert.assertNotNull(listBook);
	}
	
	@Test
	public void testdelBook() throws DAOException {
		Long id = bookDAO.saveBook(TEST_BOOK);
		bookDAO.deleteBook(id);
		Book book = bookDAO.bookById(id);
		Assert.assertEquals("0", book.getStatus());
	}
	
}



