package br.com.casare86.store.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale.Category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "products")
//there is a option for named queries in Model class and just recover it at DAO´s classes 
@NamedQuery(name = "Product.productsByName", query =  "SELECT p FROM ProductModel AS p WHERE p.name = :name")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //SAP uses this option. Every child class will be put at the same table
//SINGLE_TABLE -> one table to support every table, JOINED -> creates a table and join it among them , TABLE_PER_CLASS -> each class has its own table and its IDs
public class ProductModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	
	@ManyToOne(fetch = FetchType.LAZY)//1 category - N products 
	private CategoryModel category; //automatically adds _id to the attribute when it goes to DB
	
	private LocalDate creationTime = LocalDate.now();
	
	//methods
	public ProductModel() {	}
	
	public ProductModel(String name, String description, BigDecimal price, CategoryModel category) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public CategoryModel getCategory() {
		return category;
	}
	public void setCategory(CategoryModel category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product " + this.id + " - Description: " + this.description + " Price: $" + this.price;
	}
	
}
