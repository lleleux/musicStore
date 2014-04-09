package com.laurent.musicStore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laurent.musicStore.dao.NotificationDao;
import com.laurent.musicStore.model.Notification;
import com.laurent.musicStore.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private NotificationDao notificationDao;

	@Override
	public List<Notification> getUnreadFor(String userName) {
		return this.notificationDao.getUnreadFor(userName);
	}

	@Override
	public void markAsRead(int id) {
		Notification notification = this.notificationDao.getById(id);
		notification.setRead(true);
		this.notificationDao.update(notification);
	}
	
}
