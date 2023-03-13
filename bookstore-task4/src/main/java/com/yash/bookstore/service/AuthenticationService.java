package com.yash.bookstore.service;

import com.yash.bookstore.entity.User;

public interface AuthenticationService {

	User signInAndReturnJWT(User signInRequest);

}
