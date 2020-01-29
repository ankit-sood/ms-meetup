package com.ms.meetup.controller;

import static com.ms.meetup.constants.MeetupConstants.EVENTS_URL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.meetup.model.Event;
import com.ms.meetup.service.EventsService;

@RestController
@RequestMapping(value=EVENTS_URL)
public class EventsController {
	@Autowired
	private EventsService eventService;
	
//	@GetMapping
//	public ResponseEntity<List<Event>> getEvents() {
//		return ResponseEntity.ok(eventService.getEvents());
//	}
//
	@PatchMapping
	public ResponseEntity<String> updateEvent(@RequestBody Event event) throws Exception {
		return ResponseEntity.ok(eventService.updateEvent(event));
	}

	@PostMapping
	public ResponseEntity<String> addEvent(@RequestBody Event event) throws Exception {
		return ResponseEntity.ok(eventService.addEvent(event));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Event> getEvent(@PathVariable Long id){
		try {
			return ResponseEntity.ok(eventService.getEvent(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Event>> getEvents(){
			return ResponseEntity.ok(eventService.getEvents());
	}

	@GetMapping(params = {"categoryId"})
	public ResponseEntity<List<Event>> getEventsByCategory(@RequestParam("categoryId") Long categoryId){
		return ResponseEntity.ok(eventService.getEventByCateogry(categoryId));
	}

	@GetMapping(params = {"ids"})
	public ResponseEntity<List<Event>> getEventsByIds(@RequestParam("ids") List<Long> ids){
		return ResponseEntity.ok(eventService.getEventByIds(ids));
	}

	@GetMapping(params = {"userId"})
	public ResponseEntity<List<Event>> getEventsByUserId(@RequestParam("userId") Long userId){
		return ResponseEntity.ok(eventService.getEventByUserId(userId));
	}



}
