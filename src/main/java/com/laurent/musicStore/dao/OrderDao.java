package com.laurent.musicStore.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.laurent.musicStore.model.Order;

public interface OrderDao extends Dao<Integer, Order> {
	
	@Transactional
	List<Order> getOrdersForUser(String userName);

}