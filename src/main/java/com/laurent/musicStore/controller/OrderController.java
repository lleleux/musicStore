package com.laurent.musicStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.laurent.musicStore.model.Order;
import com.laurent.musicStore.service.OrderService;

@Controller
@SessionAttributes({"login"})
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		String login = model.asMap().get("login").toString();
		List<Order> orders = this.orderService.getAllOrders(login);
		model.addAttribute("orders", orders);
		return "orders";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addTrack(Model model, @RequestParam int trackId) {
		String login = model.asMap().get("login").toString();
		this.orderService.addOrder(login, trackId);
		return list(model);
	}
	
}
