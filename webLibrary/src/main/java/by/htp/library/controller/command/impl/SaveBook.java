package by.htp.library.controller.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.bean.Book;
import by.htp.library.controller.Command;
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
public class SaveBook implements Command {
	private static final Logger log = LogManager.getRootLogger();
	/**
	 * The method serves to retrieve the book-object for saving
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id;
		Book book = new Book();
		String page;
		page = JspManager.EDIT_BOOK;

		// checking: new or existing
		if ((request.getParameter(ParameterManager.BOOK_ID) != null)
				&& ((request.getParameter(ParameterManager.BOOK_ID) != ""))) {
			book.setId(Long.parseLong(request.getParameter(ParameterManager.BOOK_ID)));
		}
		try {
			book.setTitle(request.getParameter(ParameterManager.BOOK_TITLE));
			book.setAuthor(request.getParameter(ParameterManager.BOOK_AUTHOR));
			book.setGenre(request.getParameter(ParameterManager.BOOK_GENRE));
			book.setYear(request.getParameter(ParameterManager.BOOK_YEAR));
			book.setQuantity(Integer.parseInt(request.getParameter(ParameterManager.BOOK_QUANTITY)));
			book.setStatus(request.getParameter(ParameterManager.BOOK_STATUS));
			book.setContext(request.getParameter(ParameterManager.BOOK_CONTEXT));

			ServiceFactory factory = ServiceFactory.getInstance();
			LibraryService libraryService = factory.getLibraryService();

			id = libraryService.saveBook(book);
			book.setId(id);
			response.sendRedirect("Controller?command=listbook&bookGenre=" + book.getGenre()+"&message="+MessageManager.ADDED);

		} catch (NumberFormatException e) {
			log.error("ServiceException in SaveBook", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.INPUT);
			request.setAttribute(ParameterManager.BOOK, book);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}

		catch (ServiceException e) {
			log.error("ServiceException in SaveBook", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.ERROR);
			request.setAttribute(ParameterManager.BOOK, book);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);

		}

	}
}