package br.com.casare86.store.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clients")
public class ClientModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded //get a fragment from another class that is not an Entity
	private PersonalData personalData;
	
	
	public ClientModel(String name, String cpf) {
		this.personalData = new PersonalData(name, cpf);
	}

	public ClientModel() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonalData getPersonalData() {
		return personalData;
	}
	
	//Delegate method
	public String getName() {
		return this.personalData.getName();
	}
	
	public String getCpf() {
		return this.personalData.getCpf();
	}
}
