package br.com.regulator.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthorizationFilter
 */
@WebFilter("/company")
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
        // TODO Auto-generated constructor stub
    }
    
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
    	
    	System.out.println("AuthorizationFilter");
    	//ServletRequest/Response are interfaces prior to HttpServlet and therefore simplier that is why we need to cast it to use getSession()
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
    	HttpServletResponse response = (HttpServletResponse) servletResponse;
    	String command = request.getParameter("action");
    	
    	if (command == null)
    		command = request.getParameter("command");
    	
    	//check if the user had logged in or not
		HttpSession session = request.getSession();
		boolean unauthorizedUser = session.getAttribute("user") == null;
		boolean protectedAction = true;
		if(command != null && (command.equals("LoginForm") || command.equals("Login")))
			protectedAction = false;
		
		if(protectedAction && unauthorizedUser) {
			response.sendRedirect("company?command=LoginForm");
			return;
		}
    	
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
    
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
