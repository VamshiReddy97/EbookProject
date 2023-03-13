package com.yash.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.yash.bookstore.entity.User;
import com.yash.bookstore.security.UserPrincipal;
import com.yash.bookstore.security.jwt.JwtProviderImpl;

@Service
public class AuthenticationServiceImpl  implements AuthenticationService{

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProviderImpl jwtProviderImpl;
	
	@Override
	public User signInAndReturnJWT(User  signInRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
				);
		
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
		
		String jwt = jwtProviderImpl.generateToken(userPrincipal);
		
		User signUser = userPrincipal.getUser();
		
		signUser.setToken(jwt);
		return signUser;
	}
	
}
