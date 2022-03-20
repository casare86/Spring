package br.com.regulator.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.regulator.model.CompanyModel;
import br.com.regulator.service.PersistanceService;

/**
 * Servlet implementation class EditCompanyServlet
 */
@WebServlet("/editCompany")
public class EditCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String paramDate = request.getParameter("date");
		Integer id = Integer.valueOf(request.getParameter("id"));
		
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			date = sdf.parse(paramDate);
		} 
		catch(ParseException e) {
			throw new ServletException(e);
		}
		
		System.out.println("Alterando empresa: " + name);
		
		PersistanceService db = new PersistanceService();
		
		//fake update company since its all stored in RAM memory not in a actually DB
		CompanyModel company = db.getCompanyById(id);
		company.setName(name);
		company.setCreationDate(date);
		
		response.sendRedirect("companyList");
	}

}
