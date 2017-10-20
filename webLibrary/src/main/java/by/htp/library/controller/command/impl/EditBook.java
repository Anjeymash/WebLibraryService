package by.htp.library.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;

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
import by.htp.library.controller.datamanager.RoleManager;
import by.htp.library.service.LibraryService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;

public class EditBook implements Command {
	private static final Logger log = LogManager.getRootLogger();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id;
		Book book;
		String role;
		ArrayList<Book> foundBooks = new ArrayList<>();
		String page = JspManager.INDEX;

		id = Long.parseLong(request.getParameter(ParameterManager.BOOK_ID));
		HttpSession session = request.getSession();
		role = (String) session.getAttribute(ParameterManager.USER_ROLE);
		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService libraryService = factory.getLibraryService();
		try {
			book = libraryService.bookById(id);
			foundBooks = libraryService.listBook(book.getGenre());

			if (role == null) {
				request.setAttribute(ParameterManager.ERROR_MES, MessageManager.SIGN_IN);
				request.setAttribute(ParameterManager.LIST_BOOK, foundBooks);
			} else {
				if (role.equals(RoleManager.ADMIN)) {
					request.setAttribute(ParameterManager.BOOK, book);
					page = JspManager.EDIT_BOOK;
				} else {
					request.setAttribute(ParameterManager.LIST_BOOK, foundBooks);
					request.setAttribute(ParameterManager.ERROR_MES, MessageManager.RIGHTS);
				}
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			log.error("ServiceException in EditBook", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.JSP_ERROR);
		} catch (NullPointerException e) {
			log.error("NullPointerException in EditBook", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.ERROR);
		}

	}
}
