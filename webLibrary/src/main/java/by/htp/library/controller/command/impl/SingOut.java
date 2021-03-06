package by.htp.library.controller.command.impl;

import java.io.IOException;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.library.controller.command.Command;
/**
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public class SingOut implements Command {
	private static final String REFERER  = "Referer";
	/**
	 * The method invalidates session
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String referer = request.getHeader(REFERER);
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(referer);
		
	}
}
