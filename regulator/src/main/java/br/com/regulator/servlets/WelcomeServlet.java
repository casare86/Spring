package br.com.regulator.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/welcome")
public class WelcomeServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public WelcomeServlet() {
		System.out.println("Tomcat creates each Servlet at the first time its invoked! (LazyLoad)");
		System.out.println("Singleton Pattern, creates only one of each Servlet and keep it in memory untill the server is stoped.");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//object to return characters
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h2> This is an example using Java Servlets via PrintWriter(string) not OutputStream(binary) </h2>");
		out.println("</body>");
		out.println("</html>");
	}

}
