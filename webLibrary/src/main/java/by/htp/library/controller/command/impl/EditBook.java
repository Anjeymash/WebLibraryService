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
public class EditBook implements Command {
	private static final Logger log = LogManager.getRootLogger();
	/**
	 * The method serves to retrieve the book-object for the edit-book page
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id;
		Book book;
		String page = JspManager.EDIT_BOOK;
		id = Long.parseLong(request.getParameter(ParameterManager.BOOK_ID));
		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService libraryService = factory.getLibraryService();
		try {
			book = libraryService.bookById(id);
			request.setAttribute(ParameterManager.BOOK, book);
			
		} catch (ServiceException e) {
			log.error("ServiceException in EditBook", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.ERROR);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
