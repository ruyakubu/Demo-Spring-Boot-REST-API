package com.demo.spring.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.spring.model.AuthToken;
import com.demo.spring.repository.AuthTokenRepository;


@Transactional(readOnly = true)
@Service
public class AuthTokenServiceImpl implements AuthTokenService {

	@Autowired
	private AuthTokenRepository authTokenRepository;

	@Transactional
	@Override
	public AuthToken create(AuthToken authToken) {
		return authTokenRepository.saveAndFlush(authToken);
	}

	@Override
	public AuthToken findUserByTokenAndSeries(String token, String series) {
		return authTokenRepository.findUserByTokenAndSeries(token, series);
	}

	@Transactional
	@Override
	public void deleteByTokenAndSeries(String token, String series) {
		authTokenRepository.deleteByTokenAndSeries(token, series);
	}

	@Transactional
	@Override
	public AuthToken update(AuthToken authToken) {
		return authTokenRepository.save(authToken);
	}

	@Transactional
	@Override
	public void deleteExpiredTokens() {
		authTokenRepository.dleteTimedoutTokens();
	}

}


