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
import by.htp.library.service.exception.ServiceExceptionValid;
import by.htp.library.service.exception.ServiceExeptionEmailExist;
import by.htp.library.service.exception.ServiceExeptionLoginExist;
import by.htp.library.service.factory.ServiceFactory;

/**
 * @author Mashkouski Andrei
 * @version 1.0
 */
public class SaveUser implements Command {
	private static final Logger log = LogManager.getRootLogger();

	/**
	 * The method serves to save the user-object
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id;
		User user = new User();
		String page = JspManager.EDIT_USER;
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);

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
		request.setAttribute(ParameterManager.USER, user);
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
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.ERROR);
			dispatcher.forward(request, response);
		} catch (ServiceExceptionValid e) {
			log.error("ServiceExceptionValid in SaveUser", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.INPUT);
			dispatcher.forward(request, response);
		} catch (ServiceExeptionEmailExist e) {
			log.error("ServiceExceptionEmailExist in SaveUser", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.EMAIL_EXIST);
			dispatcher.forward(request, response);
		} catch (ServiceExeptionLoginExist e) {
			log.error("ServiceExceptionLoginExist in SaveUser", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.LOGIN_EXIST);
			dispatcher.forward(request, response);
		}

	}
}