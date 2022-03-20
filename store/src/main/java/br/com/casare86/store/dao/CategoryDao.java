package br.com.casare86.store.dao;

import javax.persistence.EntityManager;

import br.com.casare86.store.model.CategoryModel;
import br.com.casare86.store.model.ProductModel;

public class CategoryDao {
	
	private EntityManager em;
	
	public CategoryDao(EntityManager em) {
		this.em = em;
	}
	
	public void addCategory(CategoryModel category) {
		this.em.persist(category);
	}
	
	public void update(CategoryModel model) {
		this.em.merge(model);
	}
	
	private void remove(CategoryModel model) {
		model = em.merge(model);
		this.em.remove(model);
	}
}
