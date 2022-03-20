package br.com.casare86.store.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CompositeKeyId implements Serializable {
	
	private Character code;
	private String company;
	
	public CompositeKeyId() {}
	
	public CompositeKeyId(Character code, String company) {
		this.code = code;
		this.company = company;
	}

	public Character getCode() {
		return code;
	}

	public void setCode(Character code) {
		this.code = code;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
