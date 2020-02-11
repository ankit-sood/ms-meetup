package com.ms.meetup.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.ms.meetup.model.UserDetails;

@Repository
public class RedisUserRepository {
	private final HashOperations<String, Long, UserDetails> hashOperations;
	
	private final RedisTemplate<String, ?> redisTemplate;
	
	public RedisUserRepository(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = this.redisTemplate.opsForHash();
	}
	
	public void saveUserDetails(UserDetails userDetails) {
		hashOperations.put("USER", userDetails.getUserId(), userDetails);
	}
	
	public UserDetails findById(Long id) {
		return (UserDetails) hashOperations.get("USER", id);
	}
	
	public void delete(Long id) {
		hashOperations.delete("USER", id);
	}
	
	public List<UserDetails> findAll(){
		return hashOperations.values("USER");
	}
	
	public Map<Long,UserDetails> findByIds(List<Long> userIds){
		Map<Long,UserDetails> userDetailsMap = new HashMap<>();
		List<UserDetails> userDetails = hashOperations.multiGet("USER", userIds);
		for(int i=0;i<userDetails.size();i++) {
			userDetailsMap.put(userDetails.get(i).getUserId(), userDetails.get(i));
		}
		return userDetailsMap;
	}
}
