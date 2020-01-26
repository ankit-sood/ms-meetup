package com.ms.meetup.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
