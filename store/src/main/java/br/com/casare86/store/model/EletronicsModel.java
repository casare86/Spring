package br.com.casare86.store.model;

import javax.persistence.Entity;

@Entity
public class EletronicsModel extends ProductModel {
	
	private String brand;
	private Integer model;
	
	public EletronicsModel() {
	}
	
	public EletronicsModel(String brand, Integer model) {
		this.brand = brand;
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getModel() {
		return model;
	}

	public void setModel(Integer model) {
		this.model = model;
	}
	
}
