package com.demo.spring.security.impl;

import org.springframework.security.core.Authentication;

public interface AuthTokenGeneratorService {
	
	String generateToken(Authentication authentication);

	public String[] decode(String token);

}
