package com.laurent.musicStore.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.laurent.musicStore.dao.UserDao;
import com.laurent.musicStore.model.User;

@Repository
public class UserDaoImpl extends DaoImpl<Integer, User> implements UserDao {

	@Override
	public User getByLogin(String login) {
		return this.get("SELECT u FROM User u WHERE login=?", login);
	}

	@Override
	public List<User> getByTrackOrder(int trackId) {
		return this.list("SELECT DISTINCT u FROM User u, Order o, Track t WHERE t.id=? AND o.user.id=u.id AND o.track.id=t.id", trackId);
	}
	
}
