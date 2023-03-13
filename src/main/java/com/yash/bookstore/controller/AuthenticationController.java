package com.yash.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.bookstore.entity.User;
import com.yash.bookstore.service.AuthenticationService;
import com.yash.bookstore.service.UserService;

//Restful Web service

@RestController
@RequestMapping("/api/auth")  //path set in Security config class also to configure httpsecurity
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private UserService userService;


	@PostMapping("sign-up") //api/auth/sign-up
	public ResponseEntity<User> signUp(@RequestBody User user){
		
		
		if(userService.findByUsername(user.getUsername()).isPresent()) {
			
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		User newuser = userService.saveUser(user);
		
		
		return new ResponseEntity<User>(newuser,HttpStatus.CREATED);
	}
	
	@PostMapping("sign-in") //api/auth/sign-in
	public ResponseEntity<?> signIn(@RequestBody User user){
		
		return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user),HttpStatus.OK);
	}

}
