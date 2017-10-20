package by.htp.library.service.impl;

import java.util.ArrayList;

import by.htp.library.bean.Book;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.factory.DAOFactory;
import by.htp.library.service.LibraryService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.validation.ValidationData;

public class LibraryServiceImpl implements LibraryService {

	@Override
	public void delBook(long id) throws ServiceException {
		if (id == 0) {
			throw new ServiceException("Incorrect input data");
		}
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			bookDAO.deleteBook(id);
		} catch (DAOException e) {
			throw new ServiceException("DAOException in delBook ", e);

		}

	}

	@Override
	public Book bookById(Long id) throws ServiceException {
		Book foundbook = null;
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			foundbook = bookDAO.bookById(id);
			// for(int i = 0; i< foundbooks.size(); i++){
			// System.out.println(foundbooks.get(i).toString()); }

		} catch (DAOException e) {
			throw new ServiceException("DAOException in bookById ", e);
		}
		return foundbook;
	}

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

	@Override
	public Long saveBook(Book book) throws ServiceException {

		if (!ValidationData.validBook(book)) {
			throw new ServiceException("Incorrect input data");
		}
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			if (book.getId() == 0)
				return bookDAO.saveBook(book);
			else
				return bookDAO.updateBook(book);

		} catch (DAOException e) {
			throw new ServiceException("DAOException in saveBook ", e);
		}

	}

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
