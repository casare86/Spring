package br.com.regulator.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.commons.lang3.StringUtils;

//webFilter works like WebServlet and need to be mapped
@WebFilter(urlPatterns="/company")
public class MonitorFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	@Override
	public void destroy() {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("MonitorFilter");
		long before = System.currentTimeMillis();
		
		String action = request.getParameter("action");
		if (action == null)
			action = request.getParameter("command");
		
		//filter stops the request, if you don´t use the cnain it stops right here!!
		chain.doFilter(request, response);
		
		long after = System.currentTimeMillis();
		System.out.println("Execution time for action: " + action + " was: " + (after - before));
		
	}
	
}
