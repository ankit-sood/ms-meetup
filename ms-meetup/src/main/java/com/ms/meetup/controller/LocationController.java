package com.ms.meetup.controller;

import static com.ms.meetup.constants.MeetupConstants.CATEGORIES_URL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.meetup.model.Location;
import com.ms.meetup.service.LocationService;

@RestController
@RequestMapping(value="/locations")
public class LocationController {
	@Autowired
	private LocationService locationService;
	
	@GetMapping
	public ResponseEntity<List<Location>> getCategories() {
		return ResponseEntity.ok(locationService.getLocations());
	}
}
