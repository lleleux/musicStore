package com.laurent.musicStore.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.laurent.musicStore.dao.NotificationDao;
import com.laurent.musicStore.model.Notification;

@Repository
public class NotificationDaoImpl extends DaoImpl<Integer, Notification> implements NotificationDao {

	@Override
	public List<Notification> getUnreadFor(String login) {
		return this.list("SELECT n FROM Notification n WHERE n.user.login=? AND n.read=false", login);
	}
	
}
