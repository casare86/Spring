package br.com.regulator.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.regulator.model.CompanyModel;
import br.com.regulator.service.PersistanceService;

public class CompanyStrategy {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersistanceService db = new PersistanceService();
		List<CompanyModel> list = db.getCompanys();
		request.setAttribute("companys", list);
		
		return "forward:/companyList.jsp";
	}
	
	public void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));
		
		PersistanceService db = new PersistanceService();
		db.removeCompany(id);
		System.out.println("removi");
		response.sendRedirect("/regulator/company?action=list");
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		PersistanceService db = new PersistanceService();
		
		//fake update company since its all stored in RAM memory not in a actually DB
		CompanyModel company = db.getCompanyById(id);
		company.setName(name);
		company.setCreationDate(date);
		return "redirect:/regulator/company?action=list";
	}

	public String show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
		PersistanceService db = new PersistanceService();
		
		CompanyModel company = db.getCompanyById(id);
		
		request.setAttribute("company", company);
		return "forward:/formShowCompany.jsp";
	}

	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		List<CompanyModel> list = persist.getCompanys();
		request.setAttribute("companys", list);

		return "forward:companyList.jsp";
	}

	public String newCompany(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ok");
		return "forward:formNewCompany.jsp";
		
	}
}
