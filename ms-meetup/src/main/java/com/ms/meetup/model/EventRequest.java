package com.ms.meetup.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="event_request")
public class EventRequest implements Serializable{
	private static final long serialVersionUID = 3186475080708359527L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="request_id")
	private Long requestId;
	
	@Column(name="event_id")
	private Long eventId;
	
	@Column(name="userId")
	private Long userId;
	
	@Column(name="event_req_status")
	private String status;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getRequestId() {
		return requestId;
	}
	
}
