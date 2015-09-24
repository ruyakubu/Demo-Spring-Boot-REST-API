package com.demo.spring.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.demo.spring.security.UserContext;
import com.demo.spring.model.AuthToken;
import com.demo.spring.security.impl.TokenBasedAuthenticationSuccessHandlerImpl;
import com.demo.spring.security.impl.AuthTokenGeneratorService;
import com.demo.spring.security.impl.AuthTokenService;
import com.demo.spring.security.impl.NoOpAuthenticationManager;


public class TokenBasedAuthenticationFilter extends
		AbstractAuthenticationProcessingFilter {

	protected final Log logger = LogFactory.getLog(getClass());

	private static final String HEADER_SECURITY_TOKEN = "X-AuthToken";
	private final String TOKEN_FILTER_APPLIED = "TOKEN_FILTER_APPLIED";
	private AuthTokenGeneratorService authTokenGeneratorService;
	private AuthTokenService authTokenService;

	public TokenBasedAuthenticationFilter(String defaultFilterProcessesUrl,
			AuthTokenGeneratorService authTokenGeneratorService,
			AuthTokenService authTokenService) {
		super(defaultFilterProcessesUrl);
		super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(
				defaultFilterProcessesUrl));
		super.setAuthenticationManager(new NoOpAuthenticationManager());
		setAuthenticationSuccessHandler(new TokenBasedAuthenticationSuccessHandlerImpl());
		this.authTokenGeneratorService = authTokenGeneratorService;
		this.authTokenService = authTokenService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse arg1) throws AuthenticationException,
			IOException, ServletException {

		AbstractAuthenticationToken userAuthenticationToken = null;
		request.setAttribute(TOKEN_FILTER_APPLIED, Boolean.TRUE);

		String token = request.getHeader(HEADER_SECURITY_TOKEN);
		userAuthenticationToken = authenticateByToken(token);
		if (userAuthenticationToken == null)
			throw new AuthenticationServiceException("Bad Token");

		return userAuthenticationToken;
	}

	/**
	 * authenticate the user based on token
	 * 
	 * @return
	 */
	private AbstractAuthenticationToken authenticateByToken(String token) {
		if (null == token) {
			return null;
		}

		AbstractAuthenticationToken authToken = null;

		try {
			String[] tokens = authTokenGeneratorService.decode(token);

			AuthToken tokenEntry = authTokenService.findUserByTokenAndSeries(
					tokens[0], tokens[1]);
			if (null == tokenEntry) {
				return null;
			}

			UserContext securityUser = new UserContext(
					tokenEntry.getUser());

			authToken = new UsernamePasswordAuthenticationToken(
					securityUser.getUsername(), "",
					securityUser.getAuthorities());
		} catch (Exception ex) {
			logger.error("Failed to authenticate user for token" + token
					+ "{ }", ex);
		}

		return authToken;
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		if (request.getAttribute(TOKEN_FILTER_APPLIED) != null) {
			arg2.doFilter(request, response);
		} else {
			super.doFilter(arg0, arg1, arg2);
		}

	}

}