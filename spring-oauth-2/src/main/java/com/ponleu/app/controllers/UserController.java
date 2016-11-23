package com.ponleu.app.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ponleu.app.commons.utils.SecurityUtil;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@ResponseBody
	@RequestMapping(value="/me", method = RequestMethod.GET)
	public String login (HttpServletResponse response) {
		return SecurityUtil.getCurrentUserName();
	}
}
