package by.htp.library.service.impl;

import java.util.ArrayList;

import by.htp.library.bean.Book;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.factory.DAOFactory;
import by.htp.library.service.LibraryService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.exception.ServiceExceptionValid;
import by.htp.library.service.validation.ValidationData;
/**
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public class LibraryServiceImpl implements LibraryService {
	private static final int newBookId = 0;
	/**
	 * The method makes the book inactive
	 */
	@Override
	public void delBook(long id) throws ServiceException {
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			bookDAO.deleteBook(id);
		} catch (DAOException e) {
			throw new ServiceException("DAOException in delBook ", e);
		}
	}
	/**
	 * The method returns the book-object by id
	 */
	@Override
	public Book bookById(Long id) throws ServiceException {
		Book foundbook = null;
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			foundbook = bookDAO.bookById(id);

		} catch (DAOException e) {
			throw new ServiceException("DAOException in bookById ", e);
		}
		return foundbook;
	}
	/**
	 * The method returns the list of books by criteria and search-parameter
	 */
	@Override
	public ArrayList<Book> search(String searchParam, String criteria) throws ServiceException {
		ArrayList<Book> foundbooks = null;
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			foundbooks = bookDAO.search(searchParam, criteria);

		} catch (DAOException e) {
			throw new ServiceException("DAOException in search ", e);
		}
		return foundbooks;

	}
	/**
	 * The method returns the list of books
	 */
	@Override
	public ArrayList<Book> listBook(String genre) throws ServiceException {
		ArrayList<Book> foundbooks = null;
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			foundbooks = bookDAO.listBook(genre);
		} catch (DAOException e) {
			throw new ServiceException("DAOException in listBook ", e);
		}
		return foundbooks;
	}
	/**
	 * The method returns the id of the new book
	 * @throws ServiceExceptionValid 
	 */
	@Override
	public Long saveBook(Book book) throws ServiceException, ServiceExceptionValid {
		if (!ValidationData.validBook(book)) {
			throw new ServiceExceptionValid("Incorrect input data");
		}
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			if (book.getId() == newBookId) {
				return bookDAO.saveBook(book);
			} else {
				return bookDAO.updateBook(book);
			}

		} catch (DAOException e) {
			throw new ServiceException("DAOException in saveBook ", e);
		}
		
	}
	/**
	 * The method reduces the amount of available books
	 */
	@Override
	public Book bookIn(Long id) throws ServiceException {
		Book foundbook = null;
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			bookDAO.bookIn(id);

		} catch (DAOException e) {
			throw new ServiceException("DAOException in bookIn ", e);
		}
		return foundbook;
	}
}
