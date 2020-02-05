package com.ms.meetup.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.meetup.constants.MeetupConstants;
import com.ms.meetup.model.Category;
import com.ms.meetup.service.UserCategoriesService;
import com.ms.meetup.vo.UserCategoryRequestVO;

@RestController
@RequestMapping(MeetupConstants.USER_CATEGORIES_URL)
public class UserCategoriesController extends BaseController{
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	private UserCategoriesService userCategoriesService;
	
	@GetMapping
	public ResponseEntity<List<Category>> getUserCategories(@PathVariable("userId") Long userId) {
		Long userid = getUserId(httpServletRequest);
		return ResponseEntity.ok(userCategoriesService.getUserCategories(userid));
	}
	
	@PostMapping
	public ResponseEntity<String> addCategory(@PathVariable("userId") Long userId,
			@RequestBody List<UserCategoryRequestVO> userCategoryRequestVOList) throws Exception {
		Long userid = getUserId(httpServletRequest);
		return ResponseEntity.ok(userCategoriesService.addUserCategories(userid, userCategoryRequestVOList));
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> addCategory(@PathVariable("userId") Long userId,@PathVariable("categoryId") Long categoryId) throws Exception {
		return ResponseEntity.ok(userCategoriesService.deleteUserCategories(userId, categoryId));
	}
}
