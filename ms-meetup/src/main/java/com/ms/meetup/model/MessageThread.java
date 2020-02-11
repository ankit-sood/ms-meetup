package com.ms.meetup.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message_thread")
public class MessageThread implements Serializable{
	private static final long serialVersionUID = 5827292727750190856L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="thread_id")
	private Long threadId;
	
	@Column(name="message")
	private String message;
	
	@Column(name="event_id")
	private Long eventId;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="posted_at")
	private Date postedAt;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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

	public Date getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(Date postedAt) {
		this.postedAt = postedAt;
	}

	public Long getThreadId() {
		return threadId;
	}
	
	
}
