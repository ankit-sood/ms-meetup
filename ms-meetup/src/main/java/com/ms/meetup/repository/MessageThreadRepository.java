package com.ms.meetup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.meetup.model.MessageThread;

public interface MessageThreadRepository extends JpaRepository<MessageThread, Long>{
	List<MessageThread> findByEventId(Long eventId);
}
