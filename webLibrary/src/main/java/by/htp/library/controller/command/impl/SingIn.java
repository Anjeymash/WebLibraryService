package by.htp.library.controller.command.impl;

import java.io.IOException;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.bean.User;
import by.htp.library.controller.command.Command;
//import by.htp.library.controller.datamanager.JspManager;
import by.htp.library.controller.datamanager.MessageManager;
import by.htp.library.controller.datamanager.ParameterManager;
import by.htp.library.service.ClientService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.exception.ServiceExceptionValid;
import by.htp.library.service.factory.ServiceFactory;

/**
 * @author Mashkouski Andrei
 * @version 1.0
 */
public class SingIn implements Command {
	private static final Logger log = LogManager.getRootLogger();
	private static final String REFERER = "Referer";

	/**
	 * The method serves to retrieve the existing user-object and putting
	 * user-parameters into session
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;

		User user = null;
		HttpSession session = request.getSession(true);
		String referer = request.getHeader(REFERER);
		login = request.getParameter(ParameterManager.USER_LOGIN);
		password = request.getParameter(ParameterManager.USER_PASSWORD);
		ServiceFactory factory = ServiceFactory.getInstance();
		ClientService clientService = factory.getClientService();

		try {
			user = clientService.singIn(login, password);
			if (user != null) {
				session.setAttribute(ParameterManager.USER_NAME, user.getName());
				session.setAttribute(ParameterManager.USER_ID, user.getId());
				session.setAttribute(ParameterManager.USER_ROLE, user.getRole());
			} else {
				request.setAttribute(ParameterManager.ERROR_MES, MessageManager.WRONG_LOG_PASS);
			}

		} catch (ServiceException e) {
			log.error("ServiceException in SingIn", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.ERROR);
		} catch (ServiceExceptionValid e) {
			log.error("Validation login/password error", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.WRONG_LOG_PASS);
		}
		response.sendRedirect(referer);

	}
}
