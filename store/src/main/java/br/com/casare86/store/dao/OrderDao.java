package br.com.casare86.store.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.casare86.store.DTO.SalesReportVO;
import br.com.casare86.store.model.OrderModel;

public class OrderDao {
	
	private EntityManager em;
	
	public OrderDao(EntityManager em) {
		this.em = em;
	}
	
	public void addOrder(OrderModel order) {
		this.em.persist(order);
	}
	
	public OrderModel getOrderById(Long id) {
		return em.find(OrderModel.class , id);
	}
	
	public OrderModel getOrderByIdJPQL(Long id) {
		String jpql = "SELECT o FROM OrderModel as o WHERE o.id = ?1";
		return em.createQuery(jpql, OrderModel.class).getSingleResult();
	}
	
	public List<OrderModel> getAll(){
		//JPQL -Java Persistense Query language
		String jpql = "SELECT p FROM OrderModel AS p";

		//createQuery only creates the query that is am approach to SQL using OOP
		return em.createQuery(jpql, OrderModel.class).getResultList();
	}
	
	public List<OrderModel> getByClientName(String name){
		String jpql = "SELECT p FROM OrderModel AS o WHERE oclient.name = :name"; //nominal parameter
		
		return em.createQuery(jpql, OrderModel.class)
				.setParameter("name", name)
				.getResultList();
	}
	
	public List<OrderModel> getByClient(Long clientId){
		String jpql = "SELECT p FROM OrderModel AS o WHERE o.client = ?1"; //indexed parameter
		
		return em.createQuery(jpql, OrderModel.class)
				.setParameter(1, clientId)
				.getResultList();
	}
	
	public BigDecimal getTotalIncome() {
		String jpql = "SELECT SUM(o.totalValue) FROM OrderModel o";
		return em.createQuery(jpql, BigDecimal.class)
				.getSingleResult();
	}
	
	//Object[] not a good option but works
	//we can use SELECT new Type (col1, col2, col3...) to get a new kind of object or class
	public List<SalesReportVO> getSalesReport(){
		String jpql = "SELECT new br.com.casare86.store.DTO.SalesReportVO (p.name, SUM(e.quantity), MAX(o.date)) "
				+ "FROM OrderModel o "
				+ "JOIN o.orderEntries e "
				+ "JOIN e.product p "
				+ " GROUP BY p.name "
				+ " ORDER BY o.date DESC ";
		
		return em.createQuery(jpql, SalesReportVO.class)
				.getResultList();
	}
	
	public OrderModel getOrderWithClient(Long id) {
		//JOIN FETCH o.client turns the method to be eager like, loading Client info as well
		String jpql = "SELECT o FROM OrderModel o JOIN FETCH o.client WHERE o.id = id";
		return em.createQuery(jpql, OrderModel.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
