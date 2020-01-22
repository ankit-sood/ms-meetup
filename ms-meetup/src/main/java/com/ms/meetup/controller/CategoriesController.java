package com.ms.meetup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.meetup.service.CategoriesService;

@RestController
@RequestMapping(value="/categories")
public class CategoriesController {
	@Autowired
	private CategoriesService categoriesService;
	
	@GetMapping
	public ResponseEntity<List<String>> getCategories() {
		return ResponseEntity.ok(categoriesService.getCategories());
	}
	
	@PostMapping
	public ResponseEntity<String> addCategory(@RequestParam("category") String category) throws Exception {
		return ResponseEntity.ok(categoriesService.addCategory(category));
	}
}
