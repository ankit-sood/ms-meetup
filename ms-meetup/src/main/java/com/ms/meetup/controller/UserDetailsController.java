package com.ms.meetup.controller;

import java.io.IOException;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ms.meetup.model.UserDetails;
import com.ms.meetup.service.UserDetailsService;

@RestController
@RequestMapping(value="/users")
public class UserDetailsController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDetails> getUserDetails(@PathVariable("userId") Long userId) {
		try {
			return ResponseEntity.ok(userDetailsService.getUserDetails(userId));
		} catch(Exception exp) {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping("/profile/{userId}")
	public ResponseEntity<String> handlePictureUpload(@PathVariable("userId") Long userId,@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) throws IOException {
		userDetailsService.handlePictureUpload(userId, file);
		//redirectAttributes.addFlashAttribute("message","You successfully uploaded " + file.getOriginalFilename() + "!");
		return ResponseEntity.ok("Success");
	}
}
