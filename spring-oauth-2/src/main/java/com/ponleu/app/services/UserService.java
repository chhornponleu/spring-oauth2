package com.ponleu.app.services;

import com.ponleu.app.entities.User;

public interface UserService {
	public User getByUsername(String username);
	public User getByUserId(Integer userId);
	public boolean update(User user);
}
