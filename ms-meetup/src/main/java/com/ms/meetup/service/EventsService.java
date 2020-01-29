package com.ms.meetup.service;

import com.ms.meetup.model.Event;
import com.ms.meetup.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventsService {
	@Autowired
	private EventRepository eventRepository;
	
	public List<Event> getEvents() {
		return eventRepository.findAll();
	}
	
	public String addEvent(Event event) {
		eventRepository.saveAndFlush(event);
		return "Success";
	}

	public Event getEvent(Long eventId) throws Exception {
		Optional<Event> eventOp = eventRepository.findById(eventId);
		if(eventOp.isPresent())
			return eventOp.get();
		else
			throw new Exception("Not Found");
	}

	public List<Event> getEventByCateogry(Long categoryId) {
		return eventRepository.findByCategoryId(categoryId);
	}

	public List<Event> getEventByIds(List<Long> ids) {
		return eventRepository.findByEventIdIn(ids);
	}

	public List<Event> getEventByUserId(Long userId) {
		return eventRepository.findByUserId(userId);
	}

	public String updateEvent(Event event) {
		eventRepository.saveAndFlush(event);
		return "Success";
	}
}
