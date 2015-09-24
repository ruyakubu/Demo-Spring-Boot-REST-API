package com.demo.spring.security.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


public class TokenBasedAuthenticationSuccessHandlerImpl extends
		SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String context = request.getContextPath();
		String fullURL = request.getRequestURI();
		String url = fullURL.substring(fullURL.indexOf(context)
				+ context.length());

		request.getRequestDispatcher(url).forward(request, response);
	}

}