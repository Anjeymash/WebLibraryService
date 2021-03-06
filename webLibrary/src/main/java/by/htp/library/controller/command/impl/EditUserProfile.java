package by.htp.library.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.bean.User;
import by.htp.library.controller.command.Command;
import by.htp.library.controller.datamanager.JspManager;
import by.htp.library.controller.datamanager.MessageManager;
import by.htp.library.controller.datamanager.ParameterManager;
import by.htp.library.service.ClientService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;
/**
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public class EditUserProfile implements Command {
	private static final Logger log = LogManager.getRootLogger();

	/**
	 * The method serves to retrieve the user-object for the edit-user page
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id;
		User user;
		String page = JspManager.INDEX;
		HttpSession session = request.getSession();
		id = (Long) session.getAttribute(ParameterManager.USER_ID);
		ServiceFactory factory = ServiceFactory.getInstance();
		ClientService clientService = factory.getClientService();
		try {
			user = clientService.fetchById(id);
			request.setAttribute(ParameterManager.USER, user);
			page = JspManager.EDIT_USER;

		} catch (ServiceException e) {
			log.error("ServiceException in EditUser", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.ERROR);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
