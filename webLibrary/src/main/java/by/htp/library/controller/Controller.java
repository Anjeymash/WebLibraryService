package by.htp.library.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.controller.datamanager.ParameterManager;
import by.htp.library.dao.connection.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
/**
 * Servlet class Controller 
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final CommandProvider provider = new CommandProvider();
	private static final Logger log = LogManager.getRootLogger();

	public Controller() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		ConnectionPool conPool = ConnectionPool.getInstance();
		try {
			conPool.initPoolData();
		} catch (ConnectionPoolException e) {
			log.error("Exception in Connection Pool", e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter(ParameterManager.COMMAND_PARAM);
		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter(ParameterManager.COMMAND_PARAM);
		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

	public void destroy() {
		ConnectionPool conPool = ConnectionPool.getInstance();
		conPool.dispose();
	}
}
