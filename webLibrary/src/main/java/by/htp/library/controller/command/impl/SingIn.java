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
import by.htp.library.controller.Command;
import by.htp.library.controller.datamanager.JspManager;
import by.htp.library.controller.datamanager.MessageManager;
import by.htp.library.controller.datamanager.ParameterManager;
import by.htp.library.service.ClientService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;

public class SingIn implements Command {
	private static final Logger log = LogManager.getRootLogger();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;
		String page = JspManager.INDEX;
		User user = null;
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession(true);

		login = request.getParameter(ParameterManager.USER_LOGIN);
		password = request.getParameter(ParameterManager.USER_PASSWORD);
		// System.out.println(login);

		ServiceFactory factory = ServiceFactory.getInstance();
		ClientService clientService = factory.getClientService();

		try {
			System.out.println(password);
			user = clientService.singIn(login, password);

			if (user != null) {
				session.setAttribute(ParameterManager.USER_NAME, user.getName());
				session.setAttribute(ParameterManager.USER_ID, user.getId());
				session.setAttribute(ParameterManager.USER_ROLE, user.getRole());
			} else
				request.setAttribute(ParameterManager.ERROR_MES, MessageManager.WRONG_LOG_PASS);
			dispatcher = request.getRequestDispatcher(page);

		} catch (ServiceException e) {
			log.error("ServiceException in SingIn", e);
			request.setAttribute(ParameterManager.ERROR_MES, e.getMessage());
			dispatcher = request.getRequestDispatcher(page);
			
		} catch (NullPointerException e) {
			log.error("NullPointerException in Singin", e);
			page = JspManager.ERROR;
			request.setAttribute(ParameterManager.ERROR_MES, e.getMessage());
			dispatcher = request.getRequestDispatcher(page);

		} finally {
			dispatcher.forward(request, response);
		}

	}
}
