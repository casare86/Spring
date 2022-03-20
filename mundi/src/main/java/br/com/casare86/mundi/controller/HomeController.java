package br.com.casare86.mundi.controller;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casare86.mundi.enums.OrderStatus;
import br.com.casare86.mundi.model.OrderModel;
import br.com.casare86.mundi.repository.OrderRepository;

@Controller
@RequestMapping("/home")
public class HomeController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping()
	public String home(Model model) {
		List<OrderModel> orders = orderRepository.findAll();
		model.addAttribute("orders", orders);
		return "home";
	}
	
	@GetMapping("/{status}")
	public String byStatus(@PathVariable("status") String status, Model model) {
		List<OrderModel> orders = orderRepository.findByStatus(OrderStatus.valueOf(status.toUpperCase()));
		model.addAttribute("orders", orders);
		model.addAttribute("status", status);
		return "home";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redicrect:/home";
	}
}
