package by.htp.library.controller.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.htp.library.controller.Command;
import by.htp.library.controller.datamanager.JspManager;
import by.htp.library.controller.datamanager.MessageManager;
import by.htp.library.controller.datamanager.ParameterManager;
import by.htp.library.controller.datamanager.RoleManager;

public class AddBook implements Command {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = JspManager.INDEX;
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute(ParameterManager.USER_ROLE);
		if (role.equals(RoleManager.ADMIN)) {
			page = JspManager.EDIT_BOOK;
		} else {
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.RIGHTS);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
