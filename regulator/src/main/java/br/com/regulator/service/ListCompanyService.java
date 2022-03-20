package br.com.regulator.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.regulator.model.CompanyModel;

/**
 * Servlet implementation class ListCompanyService
 */
@WebServlet("/companys")
public class ListCompanyService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PersistanceService db = new PersistanceService();
		List<CompanyModel> companys =  db.getCompanys();
		
		String applicationType = request.getHeader("Accept");
		System.out.println("ApplicationType: " + applicationType);
		
		if(applicationType.endsWith("json")) {
			Gson gson = new Gson();
			String jsonResponse = gson.toJson(companys);
			
			response.setContentType("application/json");
			response.getWriter().print(jsonResponse);
		}
		else if(applicationType.endsWith("xml")) {
			XStream xtream = new XStream();
			String xmlResponse = xtream.toXML(companys);
			
			response.setContentType("application/xml");
			response.getWriter().print(xmlResponse);
		}
		else{
			response.setContentType("application/json");
			response.getWriter().print("{'message':'no content'}");
		}
	}
}
