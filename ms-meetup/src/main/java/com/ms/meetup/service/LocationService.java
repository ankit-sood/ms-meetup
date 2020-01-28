package com.ms.meetup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.meetup.model.Category;
import com.ms.meetup.model.Location;
import com.ms.meetup.repository.CategoryRepository;
import com.ms.meetup.repository.LocationRepository;

@Service
public class LocationService {
	@Autowired
	private LocationRepository locationRepository;
	
	public List<Location> getLocations() {
		return locationRepository.findAll();
	}
	
	public Location getLocationById(Long locationId) throws Exception{
		Optional<Location> locationOp = locationRepository.findById(locationId);
		if(locationOp.isPresent()) {
			return locationOp.get();
		} else {
			throw new Exception("Invalid location id.");
		}
	}
	
	public List<Location> getLocationByIds(List<Long> locationIds) {
		return locationRepository.findAllById(locationIds);
	}
}
