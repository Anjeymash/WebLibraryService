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
import by.htp.library.controller.datamanager.MessageManager;
import by.htp.library.controller.datamanager.ParameterManager;

public class Registration implements Command {
	private static final Logger log = LogManager.getRootLogger();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=null;
		try {
			dispatcher = request.getRequestDispatcher(JspManager.EDIT_USER);
			

		} catch (NullPointerException e) {
			log.error("NullPointerException in Registration. Jsp's not found", e);
			dispatcher = request.getRequestDispatcher(JspManager.ERROR);
			request.setAttribute(ParameterManager.ERROR_MES, e.getMessage());
		}
finally {dispatcher.forward(request, response);}
	}
}
