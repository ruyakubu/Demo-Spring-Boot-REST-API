package com.demo.spring.security.impl;


import java.io.IOException;







import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.demo.spring.security.CustomUserDetailsService;
import com.demo.spring.security.UserAuthentication;
import com.demo.spring.security.UserContext;

public class AuthenticationSuccessHandlerImpl extends
		SimpleUrlAuthenticationSuccessHandler {
	
	private static final String HEADER_SECURITY_TOKEN = "X-AuthToken";

	@Value("${auth.success.url}")
	private String defaultTargetUrl;

	@Autowired
	private AuthTokenGeneratorService authTokenGeneratorService;
	
	@Autowired
    private UserDetailsService userDetailsService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		// Lookup the complete User object from the database and create an Authentication for it
/*		final UserContext authenticatedUser = (UserContext) userDetailsService.loadUserByUsername(authentication.getName());
		final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

*/
		final String authToken = authTokenGeneratorService
				.generateToken(authentication);
		
		response.addHeader(HEADER_SECURITY_TOKEN, authToken);
		request.getRequestDispatcher(defaultTargetUrl).forward(request,
				response);

	}

}

