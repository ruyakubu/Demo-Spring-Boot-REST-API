package com.demo.spring.security.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class LogoutSuccessHandlerImpl extends
		AbstractAuthenticationTargetUrlRequestHandler implements
		LogoutSuccessHandler {
	
	private static final String HEADER_SECURITY_TOKEN = "X-AuthToken";

	@Autowired
	private AuthTokenService authTokenService;

	@Autowired
	private AuthTokenGeneratorService authTokenGeneratorService;

	public void onLogoutSuccess(HttpServletRequest arg0,
			HttpServletResponse arg1, Authentication arg2) throws IOException,
			ServletException {
		deleteAuthenticationToken(arg0);
		super.handle(arg0, arg1, arg2);

	}

	private void deleteAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(HEADER_SECURITY_TOKEN);
		if (null == token || token.trim().length() == 0) {
			return;
		}

		String[] tokens = authTokenGeneratorService.decode(token);
		authTokenService.deleteByTokenAndSeries(tokens[0], tokens[1]);

	}
}