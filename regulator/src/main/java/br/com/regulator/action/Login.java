package br.com.regulator.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.regulator.model.UserModel;
import br.com.regulator.service.PersistanceService;

public class Login implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		PersistanceService db = new PersistanceService();
		UserModel user = db.getUserByLogin(login, password);
		
		if(user instanceof UserModel) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return "redirect:company?action=list";
		}
		
		System.out.println("User not found!");
		return "redirect:company?command=LoginForm";
	}

}
