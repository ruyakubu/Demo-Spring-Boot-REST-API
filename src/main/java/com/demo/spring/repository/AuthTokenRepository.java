package com.demo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.spring.model.AuthToken;


@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {

	@Transactional(readOnly = true)
	@Query("select  authToken from AuthToken authToken where authToken.token= :token and authToken.series= :series")
	AuthToken findUserByTokenAndSeries(@Param("token") String token,
			@Param("series") String series);

	@Transactional
	@Modifying
	@Query("delete from AuthToken authToken where authToken.token= :token and authToken.series= :series")
	void deleteByTokenAndSeries(@Param("token") String token,
			@Param("series") String series);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete  from auth_token where 1="
			+ "case "
			+ "when (last_used_date is null and TIMESTAMPDIFF(MINUTE,created_date,sysdate()) > 2) then 1 "
			+ "when (last_used_date <> null and TIMESTAMPDIFF(MINUTE,last_modified_date,sysdate()) > 2) then 1 "
			+ "else 0 " + "end ")
	void dleteTimedoutTokens();
}
