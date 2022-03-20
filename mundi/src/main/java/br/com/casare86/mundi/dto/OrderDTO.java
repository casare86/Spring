package br.com.casare86.mundi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.com.casare86.mundi.enums.OrderStatus;
import br.com.casare86.mundi.utils.OrderConverter;

public class OrderDTO {
	
	@NotBlank @Min(3) @Max(30)
	private String name;
	
	@NotBlank
	private String urlProduct;
	
	@NotBlank
	private String imgProductUrl;
	
	private String description;
	
	private BigDecimal price;
	private LocalDate deliveryDate;
	
	private OrderConverter converter;
	
	public OrderDTO() {
		this.converter = new OrderConverter();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrlProduct() {
		return urlProduct;
	}
	public void setUrlProduct(String urlProduct) {
		this.urlProduct = urlProduct;
	}
	public String getImgProductUrl() {
		return imgProductUrl;
	}
	public void setImgProductUrl(String imgProductUrl) {
		this.imgProductUrl = imgProductUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public OrderConverter getConverter() {
		return converter;
	}

}
