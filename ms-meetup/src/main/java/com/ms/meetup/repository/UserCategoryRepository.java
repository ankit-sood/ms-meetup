package com.ms.meetup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.meetup.model.UserCategory;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long>{
	List<UserCategory> findByUserId(Long userId);
	
	UserCategory findByUserIdAndCategoryId(Long userId,Long categoryId);
}
