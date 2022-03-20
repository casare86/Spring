package br.com.casare86.store.model;

import javax.persistence.Entity;

@Entity
public class BookModel extends ProductModel {
	
	private String author;
	private Integer pages;
	
	public BookModel() {
		// TODO Auto-generated constructor stub
	}
	
	public BookModel(String author, Integer pages) {
		this.author = author;
		this.pages = pages;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}
	
}
