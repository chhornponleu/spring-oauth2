package com.ponleu.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CommonTest {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		System.out.println(encoder.matches("123", "$2a$10$JZumzy0e8EGQ6/gimNh/2OeLn.x6Mu4X.1CBH3ILE5cJDH.5f5tvS"));
		
		System.out.println("$2a$10$JZumzy0e8EGQ6/gimNh/2OeLn.x6Mu4X.1CBH3ILE5cJDH.5f5tvS".length()); 
	}
}
