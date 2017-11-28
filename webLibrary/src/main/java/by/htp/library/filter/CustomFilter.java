package by.htp.library.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Mashkouski Andrei
 * @version 1.0
 */
public class CustomFilter implements Filter {
	private static final String FILTERABLE_CONTENT_TYPE = "application/x-www-form-urlencoded";
	private static final String ENCODING_DEFAULT = "UTF-8";
	private static final String ENCODING_INIT_PARAM_NAME = "encoding";
	private String encoding;

	public void destroy() {
	}

	/**
	 * The method sets the UTF-8 encoding
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws ServletException, IOException {
		String contentType = req.getContentType();
		if (contentType != null && contentType.startsWith(FILTERABLE_CONTENT_TYPE))
			req.setCharacterEncoding(encoding);
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter(ENCODING_INIT_PARAM_NAME);
		if (encoding == null) {
			encoding = ENCODING_DEFAULT;
		}
	}
}