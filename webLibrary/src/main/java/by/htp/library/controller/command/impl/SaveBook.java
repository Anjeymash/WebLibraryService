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

public class SaveBook implements Command {
	private static final Logger log = LogManager.getRootLogger();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id;
		Book book = new Book();
		String page;

		// checking: new or existing
		if ((request.getParameter(ParameterManager.BOOK_ID) != null)
				&& ((request.getParameter(ParameterManager.BOOK_ID) != ""))) {
			book.setId(Long.parseLong(request.getParameter(ParameterManager.BOOK_ID)));
		}

		book.setTitle(request.getParameter(ParameterManager.BOOK_TITLE));
		book.setAuthor(request.getParameter(ParameterManager.BOOK_AUTHOR));
		book.setGenre(request.getParameter(ParameterManager.BOOK_GENRE));
		book.setYear(request.getParameter(ParameterManager.BOOK_YEAR));
		book.setQuantity(Integer.parseInt(request.getParameter(ParameterManager.BOOK_QUANTITY)));
		book.setStatus(request.getParameter(ParameterManager.BOOK_STATUS));
		book.setContext(request.getParameter(ParameterManager.BOOK_CONTEXT));

		System.out.println(book.toString());

		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService libraryService = factory.getLibraryService();

		try {
			id = libraryService.saveBook(book);
			book.setId(id);
			response.sendRedirect("Controller?command=listbook&bookGenre=" + book.getGenre());
		} catch (ServiceException e) {

			log.error("ServiceException in SaveBook", e);
			page = JspManager.EDIT_BOOK;
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.INPUT);
			request.setAttribute(ParameterManager.BOOK, book);
			try {
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
			} catch (NullPointerException e1) {
				log.error("NullPointerException in SaveBook", e1);
				request.setAttribute(ParameterManager.ERROR_MES, MessageManager.JSP_ERROR);
			}
		}

	}
}