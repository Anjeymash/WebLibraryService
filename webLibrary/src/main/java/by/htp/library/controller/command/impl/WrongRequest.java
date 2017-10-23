package by.htp.library.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.controller.Command;
import by.htp.library.controller.datamanager.JspManager;
import by.htp.library.controller.datamanager.ParameterManager;

public class WrongRequest implements Command {
	private static final Logger log = LogManager.getRootLogger();
	RequestDispatcher dispatcher = null;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String page = JspManager.INDEX;
		try {
			dispatcher = request.getRequestDispatcher(page);

		} catch (NullPointerException e) {
			log.error("WrongRequest error", e);
			page = JspManager.ERROR;
			dispatcher = request.getRequestDispatcher(page);
			request.setAttribute(ParameterManager.ERROR_MES, e.getMessage());
		} finally {
			dispatcher.forward(request, response);
		}

	}

}
