package com.ms.meetup.vo;

import java.io.Serializable;
import java.util.Date;

public class JwtResponseVO implements Serializable{
	private static final long serialVersionUID = 576420102963273073L;

	private final String jwttoken;
	private final Date expiresOn;
	private final String userName;
	private final Long userId;
	
	public JwtResponseVO(String jwttoken, Date expiresOn, String userName, Long userId) {
		this.jwttoken = jwttoken;
		this.expiresOn = expiresOn;
		this.userName = userName;
		this.userId = userId;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getUserName() {
		return userName;
	}

	public Date getExpiresOn() {
		return expiresOn;
	}

	public Long getUserId() {
		return userId;
	}
	
}
