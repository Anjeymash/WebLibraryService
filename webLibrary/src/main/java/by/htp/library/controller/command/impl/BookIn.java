package by.htp.library.controller.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
public class BookIn implements Command {
	private static final Logger log = LogManager.getRootLogger();
	private static final int nullQuantity = 0;

	/**
	 * The method serves to retrieve the object for the order page
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id;
		Book book = new Book();
		String page = JspManager.LIST_BOOK;
		String role;
		id = Long.parseLong(request.getParameter(ParameterManager.BOOK_ID));
		HttpSession session = request.getSession();
		role = (String) session.getAttribute(ParameterManager.USER_ROLE);
		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService libraryService = factory.getLibraryService();
	
		try {
			book = libraryService.bookById(id);
			request.setAttribute(ParameterManager.BOOK, book);
			if (role == null) {
				request.setAttribute(ParameterManager.ERROR_MES, MessageManager.SIGN_IN);

			} else if (book.getQuantity() > nullQuantity) {
			//	libraryService.bookIn(id);
				book = libraryService.bookById(id);
				request.setAttribute(ParameterManager.BOOK, book);
				request.setAttribute(ParameterManager.BOOK_ID, id);
				page = JspManager.EDIT_RENT;

			} else {
				request.setAttribute(ParameterManager.ERROR_MES, MessageManager.NO_BOOKS);
			}

		} catch (ServiceException e) {
			log.error("ServiceException in BookIn", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.ERROR);

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
