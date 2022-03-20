package br.com.regulator.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.regulator.action.Action;
import br.com.regulator.controllers.CompanyStrategy;

@WebServlet("/company")
public class CompanyControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String command = request.getParameter("command");
		String page = "";
		
		if (command != null) {
			String className = "br.com.regulator.action." + command.substring(0, 1).toUpperCase() + command.substring(1);
			System.out.println(className);
			//You can use this to implement the switch case above, using each class as one strategy to deal for each desired action
			try {
				Class actionClass = Class.forName(className); //load the class with their core name.
				Object obj = actionClass.newInstance(); // create the target Class using the generic Object 
				Action actionInterface = (Action) obj; //then cast it to an Interface to use the method
				
				page = actionInterface.execute(request, response);	
			}
			catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if(action != null) {
			CompanyStrategy company = new CompanyStrategy();
			
			switch (action) {
			case "list":
				page = company.execute(request, response);
				break;
			
			case "show":
				page = company.show(request, response);
				break; 
			
			case "remove": //remove method will automatically redirect there is why the return stead of break
				company.remove(request, response);
				return; 
				
			case "edit":
				page = company.edit(request, response);
				break;
			
			case "add":
				page = company.add(request, response);
				break;
				
			case "newCompany":
				page = company.newCompany(request, response);
				break;
			default:
				break;
			}
		}
		
		String[] pageUrl = page.split(":");
		if(pageUrl[0].equalsIgnoreCase("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher(pageUrl[1]);
			rd.forward(request, response);	
		}
		else {
			response.sendRedirect(pageUrl[1]);
		}
		
	}
}