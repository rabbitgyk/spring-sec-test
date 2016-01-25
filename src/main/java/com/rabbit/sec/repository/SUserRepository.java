package com.rabbit.sec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rabbit.sec.model.SUser;

/**
 *
 * @author rabbit
 * @date 2016年1月5日 下午12:45:53
 */
public interface SUserRepository extends JpaRepository<SUser, Integer> {
	@Query("select u from SUser u where u.email=?1 and u.password=?2")
	SUser login(String email, String password);

	SUser findByEmailAndPassword(String email, String password);

	SUser findUserByEmail(String email);
}

