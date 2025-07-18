package com.flavourheights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flavourheights.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	public User findByEmail(String username);
}
