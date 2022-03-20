package br.com.casare86.store.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate date = LocalDate.now();
	
	//xToOne is eager - entity is loaded via JOIN even if not required (performance impact)
	@ManyToOne(fetch = FetchType.LAZY)
	private ClientModel client;
	
	@OneToMany(mappedBy = "order") //itens_pedido //xToMany - lazy only do the joins if needded
	private List<OrderEntryModel> orderEntries = new ArrayList<OrderEntryModel>();
	
	@Column(name = "total_value")
	private BigDecimal totalValue = BigDecimal.ZERO;

	
	public OrderModel(ClientModel client) {
		this.client = client;
	}
	
	public OrderModel() {}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ClientModel getClient() {
		return client;
	}

	public void setClient(ClientModel client) {
		this.client = client;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal total) {
		this.totalValue = total;
	}
	
	//method required to ensure the bidirecional knowledge of each other and needs to be added correctly
	public void addEntry(OrderEntryModel item) {
		Objects.requireNonNull(item, "obj must not be null");
		item.setOrder(this); //set the order to itself
		this.orderEntries.add(item);
		this.totalValue = this.totalValue.add(item.getValue());
	}
}