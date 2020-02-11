package com.ms.meetup.vo;

import java.util.Date;

public class MessageThreadResponseVO {
	private Long threadId;
	private String message;
	private String userName;
	private Date postedAt;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(Date postedAt) {
		this.postedAt = postedAt;
	}

	public Long getThreadId() {
		return threadId;
	}
	
	public void setThreadId(Long threadId) {
		this.threadId= threadId;
	}
	
}
