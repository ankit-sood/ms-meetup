package com.ms.meetup.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.meetup.service.JwtUserDetailsService;
import com.ms.meetup.service.UserDetailsService;
import com.ms.meetup.util.JwtTokenUtil;
import com.ms.meetup.vo.JwtResponseVO;
import com.ms.meetup.vo.UserAuthenticationRequestVO;
import com.ms.meetup.vo.UserDetailsRequestVO;

@CrossOrigin
@RestController
@RequestMapping("/ketchup")
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponseVO> createAuthenticationToken(@RequestBody UserAuthenticationRequestVO authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		com.ms.meetup.model.UserDetails userDetails = userDetailsService.getUserDetailsByUsername(authenticationRequest.getUsername());
		//final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		Date expiresOn = jwtTokenUtil.getExpirationDateFromToken(token);
		
		return ResponseEntity.ok(new JwtResponseVO(token,expiresOn,userDetails.getUsername(),userDetails.getUserId()));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> registerUser(@RequestBody UserDetailsRequestVO userDetailsRequestVO) throws Exception {
		jwtUserDetailsService.saveUserDetails(userDetailsRequestVO);
		return ResponseEntity.ok("Success");
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
