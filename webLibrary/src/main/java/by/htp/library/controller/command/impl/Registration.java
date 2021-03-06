package by.htp.library.controller.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.controller.command.Command;
import by.htp.library.controller.datamanager.JspManager;

/**
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public class Registration implements Command {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(JspManager.EDIT_USER);
		dispatcher.forward(request, response);
						
	}
}
