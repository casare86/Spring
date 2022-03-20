package br.com.casare86.mundi.utils;

import java.math.BigDecimal;

import br.com.casare86.mundi.dto.OrderDTO;
import br.com.casare86.mundi.enums.OrderStatus;
import br.com.casare86.mundi.model.OrderModel;

public class OrderConverter implements Converter<OrderDTO, OrderModel> {

	@Override
	public OrderModel toEntity(OrderDTO dto) {
		
		OrderModel order = new OrderModel();
		
		order.setName(dto.getName());
		order.setPrice(dto.getPrice());
		order.setDescription(dto.getDescription());
		order.setImgProductUrl(dto.getImgProductUrl());
		order.setDeliveryDate(dto.getDeliveryDate());
		order.setUrlProduct(dto.getUrlProduct());
		
		return order;
	}

	@Override
	public OrderDTO toDTO(OrderModel entity) {
		
		OrderDTO dto = new OrderDTO();
		
		dto.setName(entity.getName());
		dto.setPrice(entity.getPrice());
		dto.setDescription(entity.getDescription());
		dto.setImgProductUrl(entity.getImgProductUrl());
		dto.setDeliveryDate(entity.getDeliveryDate());
		dto.setUrlProduct(entity.getUrlProduct());
		
		return dto;
	}

	@Override
	public OrderModel updateEntity(OrderModel entity, OrderDTO dto) {
		return null;
	}
}
