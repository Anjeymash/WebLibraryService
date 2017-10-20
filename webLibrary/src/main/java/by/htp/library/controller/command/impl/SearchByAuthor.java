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
import by.htp.library.controller.Command;
import by.htp.library.controller.datamanager.JspManager;
import by.htp.library.controller.datamanager.MessageManager;
import by.htp.library.controller.datamanager.ParameterManager;
import by.htp.library.service.LibraryService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;

public class SearchByAuthor implements Command {
	private static final Logger log = LogManager.getRootLogger();
	private final static String CRITERIA = "author";

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Book> foundBooks = new ArrayList<>();
		String page = JspManager.INDEX;
		String searchParam;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();
		searchParam = request.getParameter(ParameterManager.SEARCH_PARAM);

		try {
			foundBooks = libraryService.search(searchParam, CRITERIA);
			if (foundBooks.isEmpty())
				request.setAttribute(ParameterManager.ERROR_MES, MessageManager.NOT_EXIST);
			request.setAttribute(ParameterManager.LIST_BOOK, foundBooks);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			log.error("ServiceException in SearchByAuthor", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.INPUT);
		} catch (NullPointerException e) {
			log.error("NullPointerException in SearchByAuthor", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.JSP_ERROR);
		}

	}
}