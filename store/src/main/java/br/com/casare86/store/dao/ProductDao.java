package br.com.casare86.store.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.mysql.cj.util.StringUtils;

import br.com.casare86.store.model.OrderModel;
import br.com.casare86.store.model.ProductModel;

public class ProductDao {
	
	private EntityManager em;
	
	public ProductDao(EntityManager em) {
		this.em = em;
	}
	
	public void addProduct(ProductModel product) {
		this.em.persist(product);
	}
	
	public ProductModel getProductById(Long id) {
		return em.find(ProductModel.class , id);
	}
	
	public List<ProductModel> getAll(){
		//JPQL -Java Persistense Query language
		String jpql = "SELECT p FROM ProductModel AS p";

		//createQuery only creates the query that is am approach to SQL using OOP
		return em.createQuery(jpql, ProductModel.class).getResultList();
	}
	
	public List<ProductModel> getByName(String name){
		String jpql = "SELECT p FROM ProductModel AS p WHERE p.name = :name"; //nominal parameter
		//String jpql = "SELECT p FROM ProductModel AS p WHERE p.name = ?1";  //indexed parameter
		
		return em.createQuery(jpql, ProductModel.class)
				.setParameter("name", name)
				.getResultList();
	}
	
	//named version
	public List<ProductModel> getByNameNamedQuery(String name){
		return em.createNamedQuery("Product.productsByName", ProductModel.class)
				.setParameter("name", name)
				.getResultList();
	}
	
	public List<ProductModel> getByCategory(String name){
		String jpql = "SELECT p FROM ProductModel AS p WHERE p.category.name = ?1"; //indexed parameter
		
		return em.createQuery(jpql, ProductModel.class)
				.setParameter(1, name)
				.getResultList();
	}
	
	//recovers only  one attribute, not the entire Entity
	public BigDecimal recoverPriceById(Long id){
		String jpql = "SELECT p.price FROM ProductModel AS p WHERE p.id = ?1"; //indexed parameter
		
		return em.createQuery(jpql, BigDecimal.class)
				.setParameter(1, id)
				.getSingleResult();
	}
	
	//Dynamic Queries
	public List<ProductModel> searchByParameters(String name, BigDecimal price, LocalDate date){
		String jpql = "SELECT p FROM ProductModel p";
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery query = builder.createQuery(ProductModel.class);
		//query.from and query.select uses the same class as base if not specified otherwise
		Root<ProductModel> from = query.from(ProductModel.class);
		
		Predicate filters = builder.and();
		if(!StringUtils.isNullOrEmpty(name)) {
			filters = builder.and(filters, builder.equal(from.get("name"), name));
		}
		if(price != null) {
			filters = builder.and(filters, builder.equal(from.get("price"), price));
		}
		if(date != null) {
			filters = builder.and(filters, builder.equal(from.get("creationTime"), date));
		}
		query.where(filters);
		return em.createQuery(query).getResultList();
	}
}