package com.laurent.musicStore.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.laurent.musicStore.dao.OrderDao;
import com.laurent.musicStore.model.Order;

@Repository
public class OrderDaoImpl extends DaoImpl<Integer, Order> implements OrderDao {

	@Override
	public List<Order> getOrdersForUser(String userName) {
		return this.list("SELECT o FROM Order o WHERE o.user.login=?", userName);
	}
	
}
