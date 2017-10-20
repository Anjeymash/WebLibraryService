package by.htp.library.controller.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.htp.library.controller.Command;
import by.htp.library.controller.datamanager.JspManager;
import by.htp.library.controller.datamanager.MessageManager;
import by.htp.library.controller.datamanager.ParameterManager;
import by.htp.library.controller.datamanager.RoleManager;

public class AddBook implements Command {
	private static final Logger log = LogManager.getRootLogger();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page;
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute(ParameterManager.USER_ROLE);
		if (role.equals(RoleManager.ADMIN)) {
			page = JspManager.EDIT_BOOK;
		} else {
			page = JspManager.INDEX;
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.RIGHTS);
		}
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);

		} catch (NullPointerException e) {
			log.error("NullPointerException in SingIn. Jsp's not found", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.JSP_ERROR);
		}
	}
}
