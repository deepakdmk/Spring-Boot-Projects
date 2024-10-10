package com.deeps.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.deeps.account.dao.UserDAO;
import com.deeps.account.entity.UserEntity;

public class AuthenticationUtil {

	Authentication authentication;
	UserEntity user;

	@Autowired
	UserDAO userDao;

	public AuthenticationUtil() {
		this.authentication = SecurityContextHolder.getContext().getAuthentication();
		this.user = userDao.findByUserName(authentication.getName());
	}

}
