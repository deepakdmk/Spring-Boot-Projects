package com.deeps.pullup.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.deeps.account.entity.UserEntity;

@Entity
@Table(name = "PullUpSession")
public class PullUpSessionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int pullUpCount;
	private LocalDateTime sessionDateTime;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public PullUpSessionEntity() {
	}

	public PullUpSessionEntity(int pullUpCount, LocalDateTime sessionDateTime, UserEntity user) {
		super();
		this.pullUpCount = pullUpCount;
		this.sessionDateTime = sessionDateTime;
		this.user = user;
	}

	public PullUpSessionEntity(Long id, int pullUpCount, LocalDateTime sessionDateTime, UserEntity user) {
		super();
		this.id = id;
		this.pullUpCount = pullUpCount;
		this.sessionDateTime = sessionDateTime;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPullUpCount() {
		return pullUpCount;
	}

	public void setPullUpCount(int pullUpCount) {
		this.pullUpCount = pullUpCount;
	}

	public LocalDateTime getSessionDateTime() {
		return sessionDateTime;
	}

	public void setSessionDateTime(LocalDateTime sessionDateTime) {
		this.sessionDateTime = sessionDateTime;
	}

}
