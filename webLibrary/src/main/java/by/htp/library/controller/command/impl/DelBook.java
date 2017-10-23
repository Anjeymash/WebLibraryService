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

public class DelBook implements Command {
	private static final Logger log = LogManager.getRootLogger();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();
		long bookID = 0;
		String role;
		Book book;
		ArrayList<Book> foundBooks = new ArrayList<>();
		String page = JspManager.INDEX;

		HttpSession session = request.getSession();
		role = (String) session.getAttribute(ParameterManager.USER_ROLE);
		bookID = Long.parseLong(request.getParameter(ParameterManager.BOOK_ID));

		try {
			book = libraryService.bookById(bookID);
			foundBooks = libraryService.listBook(book.getGenre());

			if (role == null) {
				request.setAttribute(ParameterManager.LIST_BOOK, foundBooks);
				request.setAttribute(ParameterManager.ERROR_MES, MessageManager.SIGN_IN);

			} else {
				if (role.equals(RoleManager.ADMIN)) {
					libraryService.delBook(bookID);
					foundBooks = libraryService.listBook(book.getGenre());
					request.setAttribute(ParameterManager.LIST_BOOK, foundBooks);
					request.setAttribute(ParameterManager.MES, "Book " + book.getTitle() + " is deleted");

				} else {
					request.setAttribute(ParameterManager.LIST_BOOK, foundBooks);
					request.setAttribute(ParameterManager.ERROR_MES, MessageManager.RIGHTS);
				}
			}

		} catch (ServiceException e) {
			log.error("ServiceException in DelBook", e);
			request.setAttribute(ParameterManager.ERROR_MES, e.getMessage());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
