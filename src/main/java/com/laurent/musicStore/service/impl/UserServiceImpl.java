package com.laurent.musicStore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laurent.musicStore.dao.UserDao;
import com.laurent.musicStore.model.User;
import com.laurent.musicStore.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User addUser(User user) {
		if (this.userDao.getByLogin(user.getLogin()) != null) {
			return null;
		}
		return this.userDao.detach(this.userDao.persist(user));
	}

	@Override
	public boolean login(String login, String password) {
		User user = this.userDao.getByLogin(login);
		return user != null && user.getPassword().equals(password);
	}

}
