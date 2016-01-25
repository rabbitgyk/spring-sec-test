package com.rabbit.sec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabbit.sec.model.SUser;
import com.rabbit.sec.repository.SUserRepository;

/**
 *
 * @author rabbit
 * @date 2016年1月5日 下午12:17:24
 */
@Service("suserService")
public class SUserService {

	@Autowired
	private SUserRepository suserRepository;//code10

	public List<SUser> findAll() {
		return suserRepository.findAll();
	}

	public SUser create(SUser user) {
		return suserRepository.save(user);
	}

	public SUser findUserById(int id) {
		return suserRepository.findOne(id);
	}

	public SUser login(String email, String password) {
		return suserRepository.findByEmailAndPassword(email, password);
	}

	public SUser update(SUser user) {
		return suserRepository.save(user);
	}

	public void deleteUser(int id) {
		suserRepository.delete(id);
	}

	public SUser findUserByEmail(String email) {
		return suserRepository.findUserByEmail(email);
	}

}

