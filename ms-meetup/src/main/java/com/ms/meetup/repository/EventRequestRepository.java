package com.ms.meetup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.meetup.model.EventRequest;

public interface EventRequestRepository extends JpaRepository<EventRequest, Long>{
	List<EventRequest> findByEventId(Long eventId);
	
	List<EventRequest> findByEventIdAndUserId(Long eventId,Long userId);
}
