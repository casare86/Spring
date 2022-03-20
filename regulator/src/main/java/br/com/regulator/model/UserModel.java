package br.com.regulator.model;

public class UserModel {
	private String login;
	private String password;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isValid(String login, String password) {
		return this.login.equals(login) && this.password.equals(password); 
	}
}
