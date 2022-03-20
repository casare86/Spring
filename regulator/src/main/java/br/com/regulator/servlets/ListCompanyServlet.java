package br.com.regulator.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import br.com.regulator.model.CompanyModel;
import br.com.regulator.service.PersistanceService;

//if you want the servlet to load with server you need to add this info to the notation
@WebServlet(urlPatterns="/listCompanys", loadOnStartup=1)
public class ListCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public ListCompanyServlet() {
		System.out.println("Starting servlet with tomcat, thanks to loadOnStartup on notation");
	}
	
	//since it´s a target of an redirect we need a method doGet and doPost or we can leave it as service to catch both cases
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PersistanceService db = new PersistanceService();
		List<CompanyModel> list = db.getCompanys();
		request.setAttribute("companys", list);
		
		
		//requestDispatcher is a server-side redirection witch is a bad practice since it permit to do lot of processing using just one request
		//this can lead to multiple CRUD actions due to F5 in browser!!
		RequestDispatcher rd = request.getRequestDispatcher("/companyList.jsp");
		rd.forward(request, response);
		
		
		/*
		 * PrintWriter out = response.getWriter(); out.println("<html><body>");
		 * out.println("<ul>");
		 * 
		 * for (CompanyModel companyModel : list) { out.println("<li>");
		 * out.println(companyModel.getName()); out.println("</li>"); }
		 * 
		 * out.println("</ul>"); out.println("</html></body>");
		 */
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
