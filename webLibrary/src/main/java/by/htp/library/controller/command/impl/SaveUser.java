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

public class SaveUser implements Command {
	private static final Logger log = LogManager.getRootLogger();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id;
		User user = new User();
		String page;
		HttpSession session = request.getSession();

		// checking: new user or existing
		if (session.getAttribute(ParameterManager.USER_ID) != null) {
			user.setId((Long) session.getAttribute(ParameterManager.USER_ID));
			user.setRole(request.getParameter(ParameterManager.USER_ROLE));
		} else {
			user.setRole("user");
		}

		user.setLogin(request.getParameter(ParameterManager.USER_LOGIN));
		user.setPassword(request.getParameter(ParameterManager.USER_PASSWORD));
		user.setName(request.getParameter(ParameterManager.USER_NAME));
		user.setSurname(request.getParameter(ParameterManager.USER_SURNAME));
		user.seteMail(request.getParameter(ParameterManager.USER_EMAIL));
		user.setLocation(request.getParameter(ParameterManager.USER_LOCATION));
		user.setTel(request.getParameter(ParameterManager.USER_TEL));
		ServiceFactory factory = ServiceFactory.getInstance();
		ClientService clientService = factory.getClientService();

		try {
			id = clientService.saveUser(user);
			user.setId(id);
			session.setAttribute(ParameterManager.USER_ID, user.getId());
			session.setAttribute(ParameterManager.USER_NAME, user.getName());
			session.setAttribute(ParameterManager.USER_ROLE, user.getRole());
			request.setAttribute(ParameterManager.USER, user);
			response.sendRedirect("Controller?command=listbook");

		} catch (ServiceException e) {

			log.error("ServiceException in SaveUser", e);

			page = JspManager.EDIT_USER;
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.INPUT);
			request.setAttribute(ParameterManager.USER, user);
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