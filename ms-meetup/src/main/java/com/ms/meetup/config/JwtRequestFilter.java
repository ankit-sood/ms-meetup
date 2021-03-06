package com.ms.meetup.config;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ms.meetup.model.UserDetails;
import com.ms.meetup.service.JwtUserDetailsService;
import com.ms.meetup.service.UserDetailsService;
import com.ms.meetup.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			try {
				HashOperations<String,String,UserDetails> ops = this.redisTemplate.opsForHash();
				com.ms.meetup.model.UserDetails userDetails = ops.get("USER", username);
				if(userDetails==null) {
					userDetails = this.userDetailsService.getUserDetailsByUsername(username);
					ops.put("USER", userDetails.getUsername(), userDetails);
				}
				if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, new ArrayList<>());
					request.setAttribute("userId", userDetails.getUserId());
					request.setAttribute("username", userDetails.getUsername());
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			} catch(Exception e) {
				System.out.println("Exception while finding user based on username. "+e.getMessage());
			}
		}
		filterChain.doFilter(request, response);
	}

}
