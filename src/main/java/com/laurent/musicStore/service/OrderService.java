package com.laurent.musicStore.service;

import java.util.List;

import com.laurent.musicStore.model.Order;


public interface OrderService {
	
	List<Order> getAllOrders(String userName);
	
	Order addOrder(String login, int trackId);
	
}
