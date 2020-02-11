package com.ms.meetup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.meetup.service.MessageThreadService;
import com.ms.meetup.vo.MessageThreadRequestVO;
import com.ms.meetup.vo.MessageThreadResponseVO;

@RestController
@RequestMapping("/messages")
public class MessageThreadController {
	@Autowired
	private MessageThreadService messageThreadService;
	
	@PostMapping
	public ResponseEntity<String> addMessage(@RequestBody MessageThreadRequestVO messageThreadRequestVO) {
		messageThreadService.addMessage(messageThreadRequestVO);
		return ResponseEntity.ok("SUCCESS");
	}
	
	@GetMapping("/{eventId}")
	public List<MessageThreadResponseVO> getMessageInfo(@PathVariable("eventId") Long eventId) throws Exception {
		return messageThreadService.getMessageInfo(eventId);
	}
}
