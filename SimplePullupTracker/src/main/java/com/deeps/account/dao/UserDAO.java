package com.deeps.account.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deeps.account.entity.UserEntity;

@Repository
public interface UserDAO extends JpaRepository<UserEntity, Long> {
	
	UserEntity findByUserName(String username);
	
	UserEntity findByEmail(String email);


}
