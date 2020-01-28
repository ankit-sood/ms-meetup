package com.ms.meetup.vo;

import java.util.List;

public class EventRequestResponseVO {
	private Long eventId;
	private List<EventRequestVO> eventRequestsList;
	
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public List<EventRequestVO> getEventRequestsList() {
		return eventRequestsList;
	}
	public void setEventRequestsList(List<EventRequestVO> eventRequestsList) {
		this.eventRequestsList = eventRequestsList;
	}
	
}
