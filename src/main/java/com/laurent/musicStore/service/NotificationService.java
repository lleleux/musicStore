package com.laurent.musicStore.service;

import java.util.List;

import com.laurent.musicStore.model.Notification;


public interface NotificationService {
	
	List<Notification> getUnreadFor(String userName);
	
	void markAsRead(int id);
	
}
