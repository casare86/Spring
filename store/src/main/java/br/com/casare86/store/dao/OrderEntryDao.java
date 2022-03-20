package br.com.casare86.store.dao;

import javax.persistence.EntityManager;

import br.com.casare86.store.model.OrderEntryModel;

public class OrderEntryDao {

	private EntityManager em;
	
	public OrderEntryDao(EntityManager em) {
		this.em = em;
	}
	
	public void addEntry(OrderEntryModel entry) {
		this.em.persist(entry);
	}
}
