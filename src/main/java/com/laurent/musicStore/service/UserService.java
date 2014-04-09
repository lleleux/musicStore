package com.laurent.musicStore.service;

import com.laurent.musicStore.model.User;

public interface UserService {
	
	User addUser(User user);
	
	boolean login(String login, String password);

}
