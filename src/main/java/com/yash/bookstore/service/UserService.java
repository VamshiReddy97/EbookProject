package com.yash.bookstore.service;

import java.util.Optional;

import com.yash.bookstore.entity.User;

public interface UserService {

	
	public User saveUser(User user);

	Optional<User> findByUsername(String username);

	void makeAdmin(String username);
}
