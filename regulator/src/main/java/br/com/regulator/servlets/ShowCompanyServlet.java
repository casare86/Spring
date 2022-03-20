package br.com.regulator.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
@WebServlet("/showCompany")
public class ShowCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
		PersistanceService db = new PersistanceService();
		
		CompanyModel company = db.getCompanyById(id);
		
		request.setAttribute("company", company);
		RequestDispatcher rd = request.getRequestDispatcher("/formShowCompany.jsp");
		rd.forward(request, response);
	}
}
