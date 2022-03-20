package br.com.casare86.store.DTO;

import java.time.LocalDate;

public class SalesReportVO {
	
	private String productName;
	private Long quantity;
	private LocalDate lastSale;
	public SalesReportVO(String productName, Long quantity, LocalDate lastSale) {
		super();
		this.productName = productName;
		this.quantity = quantity;
		this.lastSale = lastSale;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public LocalDate getLastSale() {
		return lastSale;
	}
	public void setLastSale(LocalDate lastSale) {
		this.lastSale = lastSale;
	}
	@Override
	public String toString() {
		return "SalesReportVO [productName = " + productName + ", quantity = " + quantity + ", lastSale = " + lastSale + "]";
	}
	
	
	
}
