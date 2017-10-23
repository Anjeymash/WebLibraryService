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

public class BookIn implements Command {
	private static final Logger log = LogManager.getRootLogger();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id;
		Book book = new Book();
		String page = JspManager.LIST_BOOK;
		String role;
		RequestDispatcher dispatcher = null;

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

			} else if (book.getQuantity() > 0) {
				libraryService.bookIn(id);
				book = libraryService.bookById(id);
				request.setAttribute(ParameterManager.BOOK, book);
				request.setAttribute(ParameterManager.MES, MessageManager.RESERVED);

			} else {
				request.setAttribute(ParameterManager.ERROR_MES, MessageManager.NO_BOOKS);
			}
			dispatcher = request.getRequestDispatcher(page);
			

		} catch (ServiceException e) {
			log.error("ServiceException in BookIn", e);
			request.setAttribute(ParameterManager.ERROR_MES,  e.getMessage());
			dispatcher = request.getRequestDispatcher(page);
			
		}

		catch (NullPointerException e) {
			log.error("NullPointerException in BookinIn", e);
			request.setAttribute(ParameterManager.ERROR_MES, e.getMessage());
			page = JspManager.ERROR;
			dispatcher = request.getRequestDispatcher(page);
			
		}
		finally {dispatcher.forward(request, response);}
	}

}
