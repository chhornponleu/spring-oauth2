package com.ponleu.app.commons.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {
	
	public static String getCurrentUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (isInstanceOfUserDetails(authentication)) {
			return ((UserDetails) authentication.getPrincipal()).getUsername();
		}
		return authentication.getPrincipal().toString();
	}
	
	public static String retrieveUserName(Authentication authentication) {
		if (isInstanceOfUserDetails(authentication)) {
			return ((UserDetails) authentication.getPrincipal()).getUsername();
		}
		return authentication.getPrincipal().toString();
	}

	public static String retrievePassword(Authentication authentication) {
		if (isInstanceOfUserDetails(authentication)) {
			return ((UserDetails) authentication.getPrincipal()).getPassword();
		}
		if (authentication.getCredentials() == null) {
			return null;
		}
		return authentication.getCredentials().toString();
	}

	public static boolean isInstanceOfUserDetails(Authentication authentication) {
		if(authentication == null) {
			return false;
		}
		return authentication.getPrincipal() instanceof UserDetails;
	}
	
	
}
