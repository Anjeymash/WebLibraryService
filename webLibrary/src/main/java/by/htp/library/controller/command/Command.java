package by.htp.library.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Interface serves to execute commands
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public interface Command {
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
