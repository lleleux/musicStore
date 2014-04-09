package com.laurent.musicStore.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.laurent.musicStore.model.Notification;

public interface NotificationDao extends Dao<Integer, Notification> {
	
	@Transactional
	List<Notification> getUnreadFor(String userName);
	
}