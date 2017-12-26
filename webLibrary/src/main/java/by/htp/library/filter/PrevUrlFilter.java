package by.htp.library.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.htp.library.controller.datamanager.JspManager;
import by.htp.library.controller.datamanager.MessageManager;
import by.htp.library.controller.datamanager.ParameterManager;

/**
 * Filter class 
 * @author Mashkouski Andrei
 * @version 1.0
 */
public class PrevUrlFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
	/**
	 * The method gets the last URL from the request context and puts it into the current session
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) req).getSession(true);
		String commandParam = ((HttpServletRequest) req).getParameter(ParameterManager.COMMAND_PARAM);

		if (null == session.getAttribute(ParameterManager.ORIGIN_URL))
			session.setAttribute(ParameterManager.ORIGIN_URL, JspManager.DEFAULT_URL);

		if (null == commandParam) {
		} else {
			if ((!commandParam.equals(MessageManager.SINGIN)) && (!commandParam.equals(MessageManager.SAVEUSER))) {
				session.setAttribute(ParameterManager.ORIGIN_URL,
						((HttpServletRequest) req).getRequestURI().toString() + MessageManager.COMMAND_DELIMITER
								+ ((HttpServletRequest) req).getQueryString().toString()
										.replace(MessageManager.ERROR_EXPRESSION, MessageManager.EMPTY_SPACE));
			}
		}
		chain.doFilter(req, rsp);
	}
}
