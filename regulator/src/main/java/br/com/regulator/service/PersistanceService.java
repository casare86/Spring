package br.com.regulator.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.regulator.model.CompanyModel;
import br.com.regulator.model.UserModel;

public class PersistanceService {
	
	private static List<CompanyModel> listCompany = new ArrayList();
	private static List<UserModel> listUsers = new ArrayList();
	private static Integer idNumber = 1;
	
	//static block to add 02 Companys at the start of the static list so it will populate listCompany from the start
	static {
		CompanyModel company1 = new CompanyModel();
		company1.setName("First Company");
		company1.setId(idNumber++);
		
		CompanyModel company2 = new CompanyModel();	
		company2.setName("Second Company");
		company2.setId(idNumber++);
	
		listCompany.add(company1);
		listCompany.add(company2);
		
		UserModel user1 = new UserModel();
		user1.setLogin("admin");
		user1.setPassword("123");
		
		//user with only basic - wont be able to access anything
		UserModel user2 = new UserModel();
		user2.setLogin("John");
		user2.setPassword("master");
		
		UserModel user3 = new UserModel();
		user2.setLogin("user");
		user2.setPassword("123");
		
		listUsers.add(user1);
		listUsers.add(user2);
		listUsers.add(user3);
	}
	
	public void newItem(CompanyModel company) {
		company.setId(PersistanceService.idNumber++);
		listCompany.add(company);
	}
	
	public List<CompanyModel> getCompanys(){
		return PersistanceService.listCompany;
	}

	public void removeCompany(Integer id) {
		
		Iterator<CompanyModel> it = listCompany.iterator();
		
		//to remove itens from an arrayList you need to use Iterator. Can´t use the FOR!!
		while(it.hasNext()){
			CompanyModel company = it.next();
			if(company.getId() == id) {
				it.remove();
			}
		}
	}

	public CompanyModel getCompanyById(Integer id) {
		for (CompanyModel company : listCompany) {
			if(company.getId() == id)
				return company;
		}
		return null;
	}

	public UserModel getUserByLogin(String login, String password) {
		for(UserModel user : listUsers) {
			if(user.isValid(login, password))
				return user;
		}
		return null;
	}
}