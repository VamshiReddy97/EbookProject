package com.yash.bookstore.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yash.bookstore.entity.Role;
import com.yash.bookstore.entity.User;
import com.yash.bookstore.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.USER);
		user.setCreateTime(LocalDateTime.now());
		
		return userRepository.save(user);
	}
	
	
	@Override
	public Optional<User> findByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	//transactional required when executing update/delete query
	
	@Override
	@Transactional
	public void makeAdmin(String username) {
		userRepository.updateUserRole(username, Role.ADMIN);
	}
}
