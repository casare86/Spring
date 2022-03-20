package br.com.casare86.mundi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;

import br.com.casare86.mundi.enums.OrderStatus;
import br.com.casare86.mundi.model.OrderModel;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long>{
	
	public List<OrderModel> findAll();
		// If not using JPA you need to build the query 
		// Query query = entityManager.createQuery("SELECT o FROM OrderModel o", OrderModel.class);
		// return query.getResultList();

	public List<OrderModel> findByStatus(OrderStatus status);
}
