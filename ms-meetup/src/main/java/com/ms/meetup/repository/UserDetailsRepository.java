package com.ms.meetup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.meetup.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{

}
