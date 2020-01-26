package com.ms.meetup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.meetup.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	UserProfile findByUserId(Long userId);
}
