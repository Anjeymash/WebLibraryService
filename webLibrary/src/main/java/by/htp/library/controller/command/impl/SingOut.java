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

public class SingOut implements Command {
	private static final Logger log = LogManager.getRootLogger();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.invalidate();
		String page = JspManager.INDEX;
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (NullPointerException e) {
			log.error("NullPointerException in SingOut", e);
			request.setAttribute(ParameterManager.ERROR_MES, MessageManager.JSP_ERROR);
		}
	}
}
