package com.ms.meetup.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/groups")
public class GroupController {
	
	@GetMapping("/{id}")
	public ResponseEntity<String> getOrdersById(@PathVariable("id")Long orderId) throws Exception {
		return ResponseEntity.ok("Success");
	}
}
