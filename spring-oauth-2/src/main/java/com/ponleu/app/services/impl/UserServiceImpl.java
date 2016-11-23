package com.ponleu.app.services.impl;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ponleu.app.daos.UserDao;
import com.ponleu.app.entities.User;
import com.ponleu.app.services.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Inject
	private UserDao userDao;

	@Override
	public User getByUsername(String username) {
		User user = null;
		try {
			user = userDao.getByUsername(username);
		} catch (HibernateException e) {
			logger.error("Error getting user by name " + username, e);
		}
		return user;
	}

	@Override
	public User getByUserId(Integer userId) {
		User user = null;
		try {
			user = userDao.get(userId);
		} catch (HibernateException e) {
			logger.error("Error getting user by id " + userId, e);
		}
		return user;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(User user) {
		boolean result = true;
		try {
			userDao.update(user);
		} catch (HibernateException e) {
			logger.error("Error updating user " + user.getId(), e);
		}
		return result;
	}

}
