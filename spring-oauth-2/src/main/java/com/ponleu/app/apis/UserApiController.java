package com.ponleu.app.apis;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ponleu.app.commons.utils.SecurityUtil;
import com.ponleu.app.entities.User;
import com.ponleu.app.services.UserService;

@RestController
@RequestMapping(value = "/apis/users")
public class UserApiController {

	@Inject
	private UserService userService;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public User getProfile() {
		String username = SecurityUtil.getCurrentUserName();
		return userService.getByUsername(username);

	}

}
