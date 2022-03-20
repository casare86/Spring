package br.com.casare86.mundi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casare86.mundi.dto.OrderDTO;
import br.com.casare86.mundi.enums.OrderStatus;
import br.com.casare86.mundi.model.OrderModel;
import br.com.casare86.mundi.repository.OrderRepository;

@Controller
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/form") //order/form
	public String form(OrderDTO orderRequest) {
		return "order/form";
	}
	
	@PostMapping("new") //@RequestMapping(method = RequestMethod.POST, value="new")
	public String newOrder(@Valid OrderDTO orderRequest, BindingResult result) {
		
		if(result.hasErrors()) {
			return "order/form";
		}
		
		OrderModel order = orderRequest.getConverter().toEntity(orderRequest);
		order.setStatus(OrderStatus.WAITING);
		orderRepository.save(order);
		return "order/form";
	}
}
