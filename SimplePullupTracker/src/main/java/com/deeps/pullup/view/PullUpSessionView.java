package com.deeps.pullup.view;

public class PullUpSessionView {

	private int index;
	private int pullUpCount;
	private String sessionDateTime;
	private Long identity;

	public PullUpSessionView() {
		super();
	}

	public PullUpSessionView(int index, int pullUpCount, String sessionDateTime, Long identity) {
		super();
		this.index = index;
		this.pullUpCount = pullUpCount;
		this.sessionDateTime = sessionDateTime;
		this.identity = identity;
	}

	public Long getIdentity() {
		return identity;
	}

	public void setIdentity(Long identity) {
		this.identity = identity;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getPullUpCount() {
		return pullUpCount;
	}

	public void setPullUpCount(int pullUpCount) {
		this.pullUpCount = pullUpCount;
	}

	public String getSessionDateTime() {
		return sessionDateTime;
	}

	public void setSessionDateTime(String sessionDateTime) {
		this.sessionDateTime = sessionDateTime;
	}

}
