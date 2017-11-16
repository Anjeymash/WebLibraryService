package by.htp.library.controller.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.htp.library.controller.Command;
import by.htp.library.controller.datamanager.JspManager;
/**
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public class WrongRequest implements Command {

	RequestDispatcher dispatcher = null;
		
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String page = JspManager.INDEX;
		dispatcher = request.getRequestDispatcher(page);
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}

}
