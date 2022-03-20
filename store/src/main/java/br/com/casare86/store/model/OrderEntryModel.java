package br.com.casare86.store.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_entries")
public class OrderEntryModel {
	//ATTENTION: JPA uses BigInteger(20) to set Long what can lead to error if your tables uses int(11) as ID for example 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private BigDecimal unitPrice;
	private Integer quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private OrderModel order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ProductModel product;

	public OrderEntryModel(Integer quantity, OrderModel order, ProductModel product) {
		this.quantity = quantity;
		this.order = order;
		this.product = product;
		this.unitPrice = product.getPrice(); //set the price as its created
	}
	
	public OrderEntryModel(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public OrderModel getOrder() {
		return order;
	}

	public void setOrder(OrderModel order) {
		this.order = order;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public BigDecimal getValue() {
		return this.unitPrice.multiply(new BigDecimal(this.quantity));
	}
	
}
