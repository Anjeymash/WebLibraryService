package by.htp.library.controller.command.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.htp.library.bean.Book;
import by.htp.library.bean.Rent;
import by.htp.library.controller.command.Command;
import by.htp.library.controller.datamanager.JspManager;
import by.htp.library.controller.datamanager.MessageManager;
import by.htp.library.controller.datamanager.ParameterManager;
import by.htp.library.service.LibraryService;
import by.htp.library.service.RentService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.exception.ServiceExceptionValid;
import by.htp.library.service.factory.ServiceFactory;

/**
 * @author Mashkouski Andrei
 * @version 1.0
 */
public class SaveRent implements Command {
	private static final Logger log = LogManager.getRootLogger();
	private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy-mm-dd");

	/**
	 * The method serves to retrieve the rent-object for saving
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long bookId;
		Long userId;
		Long rentId;
		Date start;
		Date end;
		String page = JspManager.EDIT_RENT;
		HttpSession session = request.getSession();
		Rent rent = new Rent();
		Book book = null;
		ServiceFactory factory = ServiceFactory.getInstance();
		try {
			LibraryService libraryService = factory.getLibraryService();
			RentService rentService = factory.getRentService();
			bookId = Long.parseLong(request.getParameter(ParameterManager.BOOK_ID));
			book = libraryService.bookById(bookId);
			userId = (Long) session.getAttribute(ParameterManager.USER_ID);

			if ((request.getParameter(ParameterManager.RENT_ID) != null)
					&& ((request.getParameter(ParameterManager.RENT_ID) != ""))) {
				rent.setRentId(Long.parseLong(request.getParameter(ParameterManager.RENT_ID)));
			}
			String startTimeStr = request.getParameter(ParameterManager.RENT_START);
			String endTimeStr = request.getParameter(ParameterManager.RENT_END);

			start = DATEFORMAT.parse(startTimeStr);
			end = DATEFORMAT.parse(endTimeStr);
			rent.setStart(start);
			rent.setEnd(end);
			rent.setUserId(userId);
			rent.setBookId(bookId);
			rentId = rentService.saveRent(rent);
			libraryService.bookIn(bookId);
			rent.setRentId(rentId);
			response.sendRedirect(
					"Controller?command=listbook&bookGenre=" + book.getGenre() + "&message=" + MessageManager.RESERVED);

		} catch (ServiceException e) {
			log.error("ServiceException in ShowBook", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.ERROR);
			request.setAttribute(ParameterManager.BOOK, book);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		
		} catch (ServiceExceptionValid|ParseException e) {
			log.error("ServiceExceptionValid in SaveUser", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.INPUT);
			request.setAttribute(ParameterManager.BOOK, book);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}

	}
}
