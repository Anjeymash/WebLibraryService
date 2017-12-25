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
public class ListBook implements Command {
	private static final Logger log = LogManager.getLogger(ListBook.class);

	/**
	 * The method serves to retrieve the list-book-object
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Book> foundBooks = new ArrayList<>();
		String page = JspManager.INDEX;
		String genre;
		String mes;
		int pageId;
		int nextPageId;
		int limit;

		if (request.getParameter(ParameterManager.BOOK_PAGE) == null) {
			pageId = 1;
		} else {
			pageId = Integer.parseInt(request.getParameter(ParameterManager.BOOK_PAGE));
		}
		genre = request.getParameter(ParameterManager.BOOK_GENRE);
		mes = request.getParameter(ParameterManager.MES);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();
		try {
			foundBooks = libraryService.listBook(genre, pageId);
			nextPageId = libraryService.getNextPageId();
			limit = libraryService.getLimit();
			request.setAttribute(ParameterManager.LIST_BOOK, foundBooks);
			request.setAttribute(ParameterManager.MES, mes);
			request.setAttribute(ParameterManager.BOOK_GENRE, genre);
			request.setAttribute(ParameterManager.BOOK_PAGE, nextPageId);
			request.setAttribute(ParameterManager.BOOK_PAGE_LIMIT, limit);

		} catch (ServiceException e) {
			log.error("ServiceException in ListBook", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.ERROR);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
