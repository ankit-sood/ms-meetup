package com.ms.meetup.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ms.meetup.model.UserDetails;
import com.ms.meetup.model.UserProfile;
import com.ms.meetup.repository.UserDetailsRepository;
import com.ms.meetup.repository.UserProfileRepository;

@Service
public class UserDetailsService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private UserProfileRepository userProfileRepository;

	public UserDetails getUserDetails(Long userId) throws Exception{
		Optional<UserDetails> userDetailsOp = userDetailsRepository.findById(userId);
		if(userDetailsOp.isPresent()) {
			return userDetailsOp.get();
		}else {
			throw new Exception("User Details not found for Id.");
		}
	}
	
	public UserDetails getUserDetailsByUsername(String username) throws Exception {
		List<com.ms.meetup.model.UserDetails> users = userDetailsRepository.findByUsername(username);
		if (CollectionUtils.isEmpty(users)) {
			throw new Exception("User not found with username: " + username);
		}else {
			com.ms.meetup.model.UserDetails user = users.get(0);
			return user;
		}
	}
	
	public Map<Long,UserDetails> getUserDetails(List<Long> userIdList) throws Exception{
		List<UserDetails> userDetailsList = userDetailsRepository.findAllById(userIdList);
		if(!CollectionUtils.isEmpty(userDetailsList)) {
			Map<Long, UserDetails> userDetailsMap = userDetailsList.stream()
			         .collect(Collectors.toMap(UserDetails::getUserId, Function.identity()));
			return userDetailsMap;
		}else {
			return null;
		}
	}
	
	public void handlePictureUpload(Long userId,MultipartFile file) throws IOException {
		UserProfile userProfile = userProfileRepository.findByUserId(userId);
		if(userProfile==null) {
			userProfile = new UserProfile();
			userProfile.setUserId(userId);
			
		}
		userProfile.setProfilePic(file.getBytes());
		userProfileRepository.save(userProfile);
	}
}
