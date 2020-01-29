package com.ms.meetup.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.meetup.service.EventRequestService;
import com.ms.meetup.vo.EventRequestVO;

@RestController
@RequestMapping(value="/events/requests")
public class EventRequestController {
	@Autowired
	private EventRequestService eventRequestService;
	
	@PostMapping
	public ResponseEntity<Map<String,String>> joinEvent(@RequestBody EventRequestVO eventRequestVO) {
		String message = eventRequestService.joinEvent(eventRequestVO, eventRequestVO.getEventId());
		Map<String,String> successMap = new HashMap<>();
		successMap.put("message",message);
		return ResponseEntity.ok(successMap);
	}
	
	@PutMapping
	public ResponseEntity<Map<String,String>> acceptEventRequests(@RequestBody EventRequestVO eventRequestVO) throws Exception {
		String message = eventRequestService.acceptEventRequests(eventRequestVO.getEventId(), eventRequestVO.getUserId());
		Map<String,String> successMap = new HashMap<>();
		successMap.put("message",message);
		return ResponseEntity.ok(successMap);
	}
	
	@GetMapping("/{eventId}")
	public List<EventRequestVO> getRequestForEvent(@PathVariable("eventId") Long eventId) throws Exception {
		return eventRequestService.getRequestForEvent(eventId);
	}
}
