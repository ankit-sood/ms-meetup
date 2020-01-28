package com.ms.meetup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ms.meetup.model.Category;
import com.ms.meetup.model.UserCategory;
import com.ms.meetup.repository.UserCategoryRepository;
import com.ms.meetup.vo.UserCategoryRequestVO;

@Service
public class UserCategoriesService {
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private UserCategoryRepository userCategoryRepository;
	
	public List<Category> getUserCategories(Long userId) {
		List<UserCategory> userCategoryList = userCategoryRepository.findByUserId(userId);
		if(!CollectionUtils.isEmpty(userCategoryList)) {
			List<Long> categoryIdList = userCategoryList.stream().map(t -> t.getCategoryId()).collect(Collectors.toList());
			return categoriesService.getCategoriesByIds(categoryIdList);
		}else {
			return new ArrayList<Category>();
		}
	}
	
	public String addUserCategories(Long userId,List<UserCategoryRequestVO> userCategoryRequestVOList) {
		List<UserCategory> userCategoryList = getUserCategoryFromVO(userId, userCategoryRequestVOList);
		userCategoryRepository.saveAll(userCategoryList);
		return "SUCCESS";
	}
	
	public String deleteUserCategories(Long userId,Long categoryId) {
		UserCategory userCategory = userCategoryRepository.findByUserIdAndCategoryId(userId, categoryId);
		if(userCategory!=null) {
			userCategoryRepository.delete(userCategory);
		}
		return "SUCCESS";
	}
	
	private List<UserCategory> getUserCategoryFromVO(Long userId, List<UserCategoryRequestVO> userCategoryRequestVOList) {
		List<UserCategory> userCategoryList = new ArrayList<>();
		for(UserCategoryRequestVO userCategoryRequestVO: userCategoryRequestVOList) {
			UserCategory userCategory = new UserCategory();
			userCategory.setCategoryId(userCategoryRequestVO.getCategoryId());
			userCategory.setUserId(userId);
			userCategoryList.add(userCategory);
		}
		return userCategoryList;
	}
	
}
