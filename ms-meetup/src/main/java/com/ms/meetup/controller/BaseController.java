package com.ms.meetup.controller;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
	
	public Long getUserId(HttpServletRequest request) {
		return (Long)request.getAttribute("userId");
	}
	
}
