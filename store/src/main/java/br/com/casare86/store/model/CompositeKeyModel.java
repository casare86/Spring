package br.com.casare86.store.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CompositeKeyExample")
public class CompositeKeyModel {
	
	@EmbeddedId
	private CompositeKeyId id;
	
	private String state;
	private String companyName;
	
	public CompositeKeyModel() {}
	
	public CompositeKeyModel(Character code, String company, String state, String companyName) {
		this.id = new CompositeKeyId(code, company);
		this.state = state;
		this.companyName = companyName;
	}

	public CompositeKeyId getId() {
		return id;
	}

	public void setId(CompositeKeyId id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
