package com.laurent.musicStore.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.laurent.musicStore.model.User;

public interface UserDao extends Dao<Integer, User> {
	
	@Transactional
	User getByLogin(String login);
	
	@Transactional
	List<User> getByTrackOrder(int trackId);

}
