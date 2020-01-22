package com.ms.meetup.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CategoriesService {
	private static List<String> categoriesList = new ArrayList<>();
	
	static {
		categoriesList.add("Technology");
		categoriesList.add("Sports");
		categoriesList.add("Cultural");
		categoriesList.add("Yoga");
	}
	
	public List<String> getCategories() {
		return categoriesList;
	}
	
	public String addCategory(String category) {
		categoriesList.add(category);
		return "Success";
	}
}
