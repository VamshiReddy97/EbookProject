package com.yash.bookstore.security.jwt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.yash.bookstore.security.UserPrincipal;

public interface JwtProvider {

	boolean validateToken(HttpServletRequest request);

	Authentication getAuthentication(HttpServletRequest request);

	String generateToken(UserPrincipal auth);

}
