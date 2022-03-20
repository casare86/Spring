package br.com.casare86.store.model;

import javax.persistence.Embeddable;

@Embeddable //expose the class to another classes that is Entities to use it as part of themselves
public class PersonalData {
	private String name;
	private String cpf;
	
	public PersonalData() {}
	
	public PersonalData(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
