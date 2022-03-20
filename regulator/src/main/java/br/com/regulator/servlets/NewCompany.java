package br.com.regulator.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.regulator.model.CompanyModel;
import br.com.regulator.service.PersistanceService;

@WebServlet("/newCompany")
public class NewCompany extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public NewCompany() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name"); 
		String strCreationDate = request.getParameter("date"); 
		
		Date creationDate = null;
		try 
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			creationDate = sdf.parse(strCreationDate);
		}
		catch (ParseException e) 
		{
			throw new ServletException(e);
		}

		CompanyModel company = new CompanyModel();
		company.setName(name);
		company.setCreationDate(creationDate);
		
		PersistanceService persist = new PersistanceService();
		persist.newItem(company);

		//if not using JSP you have to create the HTML output this way
		/*
		 * PrintWriter out = response.getWriter();
		 * out.println("<html><body> The Company <b> " + name +
		 * " </b> was successfully added!</body></html>"); infoMethod(request,
		 * response);
		 */
		
		//using JSP - RequestDispatcher -> ServerSide redirect (not a good practice
//		RequestDispatcher rd = request.getRequestDispatcher("/listCompanys"); //setup JSP´s URL 
//		request.setAttribute("companyName", company.getName()); //(string, obj);
//		rd.forward(request,  response); //sent do another page or the URL set
		
		response.sendRedirect("listCompanys"); //302 - redirect to a new URL or JSP in this case ListCompanyServlet.java -> CompanyList.jsp
	}

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	   String name = req.getParameter("name");
	   PrintWriter out = resp.getWriter();
	   out.println("<html><body> <b>"  + name + " </b> wasn�t added because the application was expecting a post!");
	   infoMethod(req, resp);
	}
    
	/* SERVICE METHOD PRECEEDS doGet and doPost so you must NOT implement both to you servlet
	 * 
	 * @Override //service applies to both GET and POST use doPost/doGet to specify
	 * protected void service(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * 
	 * boolean isPost = "POST".equals(request.getMethod());
	 * 
	 * PrintWriter out = response.getWriter(); //getParameter() only return strings
	 * so you need a Parser if you want an Int or Double values String name =
	 * request.getParameter("name");
	 * 
	 * if (isPost) { out.println("<html><body> The Company <b> " + name +
	 * " </b> was successfully added!</body></html>"); } else {
	 * out.println("<html><body> <b>" + name +
	 * " </b> wasn�t added because the application was expecting a post!"); } }
	 */
    
    protected void infoMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	PrintWriter out = response.getWriter();
    	out.append("</br>Method used: ");
		out.append(request.getMethod());
		out.append("</br><a href='formNewCompany.html'><button> Add new Company </button></a>");
		out.append("</br><a href='listCompanys'><button> View all Companys </button></a>");
    }
}
