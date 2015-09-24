package com.demo.spring.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.demo.spring.security.TokenBasedAuthenticationFilter;
import com.demo.spring.security.impl.AuthTokenGeneratorService;
import com.demo.spring.security.impl.AuthTokenService;


@Configuration
@EnableWebSecurity
@Order(2)
public class APISecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthTokenGeneratorService authTokenGeneratorService;

	@Autowired
	private AuthTokenService authTokenService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/api/**")
				.csrf()
				.disable()
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.addFilterBefore(tokenBasedAuthenticationFilter(),
						BasicAuthenticationFilter.class).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.exceptionHandling()
				.authenticationEntryPoint(new Http403ForbiddenEntryPoint());
	}

	@Bean
	public TokenBasedAuthenticationFilter tokenBasedAuthenticationFilter() {
		return new TokenBasedAuthenticationFilter("/api/**",
				authTokenGeneratorService, authTokenService);
	}
}

