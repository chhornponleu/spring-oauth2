package com.ponleu.app.daos;

import com.ponleu.app.entities.User;
import com.ponleu.app.generics.GenericDao;

public interface UserDao extends GenericDao<User> {
	public User getByUsername(String username);
}
