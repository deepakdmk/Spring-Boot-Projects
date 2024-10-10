package com.deeps.account.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.deeps.account.dao.UserDAO;
import com.deeps.account.entity.RoleEntity;
import com.deeps.account.entity.UserEntity;
import com.deeps.account.view.UserRegistrationView;

@Service
public class RegistrationService {

	@Autowired
	UserDAO userDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserEntity convertViewToEntity(UserRegistrationView view) {
		if (checkForDuplicateUsername(view) || checkForDuplicateEmail(view)) {
			return null;
		} else {
			UserEntity user = new UserEntity(view.getFirstName(), view.getLastName(), view.getUserName(), view.getEmail(),
					passwordEncoder.encode(view.getPassword()), Arrays.asList(new RoleEntity("USER")));
			return userDao.save(user);

		}

	}

	private boolean checkForDuplicateUsername(UserRegistrationView view) {
		UserEntity entity = new UserEntity();
		entity = userDao.findByUserName(view.getUserName());
		if (entity != null) {
			return true;
		}
		return false;
	}

	private boolean checkForDuplicateEmail(UserRegistrationView view) {
		UserEntity entity = new UserEntity();
		entity = userDao.findByEmail(view.getEmail());
		if (entity != null) {
			return true;
		}
		return false;
	}

}
