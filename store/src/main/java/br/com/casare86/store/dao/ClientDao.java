package br.com.casare86.store.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.casare86.store.model.ClientModel;

public class ClientDao {
	
	private EntityManager em;
	
	public ClientDao(EntityManager em) {
		this.em = em;
	}
	
	public void addClient(ClientModel client) {
		this.em.persist(client);
	}
	
	public ClientModel getClientById(Long id) {
		return em.find(ClientModel.class , id);
	}
	
	public List<ClientModel> getAll(){
		//JPQL -Java Persistense Query language
		String jpql = "SELECT c FROM ClientModel AS c";

		//createQuery only creates the query that is am approach to SQL using OOP
		return em.createQuery(jpql, ClientModel.class).getResultList();
	}
	
	public List<ClientModel> getByName(String name){
		String jpql = "SELECT c FROM ClientModel AS c WHERE p.name = :name"; //nominal parameter
		//String jpql = "SELECT p FROM ClientModel AS p WHERE p.name = ?1";  //indexed parameter
		
		return em.createQuery(jpql, ClientModel.class)
				.setParameter("name", name)
				.getResultList();
	}
	
}
