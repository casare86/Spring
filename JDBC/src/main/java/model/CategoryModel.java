package model;

import java.util.ArrayList;
import java.util.List;

public class CategoryModel {
	
	private Integer id;
	private String name;
	private List<ProductModel> products = new ArrayList<ProductModel>();

	public CategoryModel(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addProduct(ProductModel product) {
		this.products.add(product);
	}
	
	public List<ProductModel> getProducts() {
		return products;
	}
}
