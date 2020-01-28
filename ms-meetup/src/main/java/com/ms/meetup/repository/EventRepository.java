package com.ms.meetup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.meetup.model.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>{
    List<Event> findByCategoryId(Long categoryId);
    List<Event> findByEventIdIn(List<Long> ids);
    List<Event> findByUserId(Long userId);
}
