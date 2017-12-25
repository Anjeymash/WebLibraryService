package by.htp.library.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.htp.library.bean.Book;
import by.htp.library.controller.command.Command;
import by.htp.library.controller.datamanager.JspManager;
import by.htp.library.controller.datamanager.MessageManager;
import by.htp.library.controller.datamanager.ParameterManager;
import by.htp.library.service.LibraryService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;
/** 
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public class DelBook implements Command {
	private static final Logger log = LogManager.getRootLogger();
	private static final int DEFAULT_OFFSET = 1;
	/**
	 * The method serves to delete the book-object 
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();
		long bookID = 0;
		int limit;
		Book book;
		ArrayList<Book> foundBooks = new ArrayList<>();
		String page = JspManager.INDEX;

		bookID = Long.parseLong(request.getParameter(ParameterManager.BOOK_ID));

		try {
			book = libraryService.bookById(bookID);
			libraryService.delBook(bookID);
			foundBooks = libraryService.listBook(book.getGenre(),DEFAULT_OFFSET);
			limit = libraryService.getLimit();
			request.setAttribute(ParameterManager.LIST_BOOK, foundBooks);
			request.setAttribute(ParameterManager.BOOK_PAGE_LIMIT, limit);
			request.setAttribute(ParameterManager.MES, "Book " + book.getTitle() + " is deleted");
			request.setAttribute(ParameterManager.BOOK_GENRE, book.getGenre());

		} catch (ServiceException e) {
			log.error("ServiceException in DelBook", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.ERROR);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
