package com.yash.bookstore.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yash.bookstore.util.SecurityUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalApiAuthenticationFilter extends OncePerRequestFilter {

	private final String accessKey;

	public InternalApiAuthenticationFilter(String accessKey) {

		this.accessKey = accessKey;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getRequestURI().startsWith("/api/internal");
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
		String requestKey = SecurityUtil.extractAuthTokenRequest(request);

		if (requestKey == null || !requestKey.equals(accessKey)) {

			log.warn("Internal key endpoint request is bad check the key again", request.getRequestURI());

			throw new RuntimeException("UNAUTHORIZED");
		}

		UserPrincipal userPrincipal = UserPrincipal.createSuperUser();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userPrincipal,
				null, userPrincipal.getAuthorities());
	
	SecurityContextHolder.getContext().setAuthentication(authentication);
		}catch (Exception e) {
			// TODO: handle exception
			
			logger.error("Counld't set user authentication insecurity context",e);
		}
		filterChain.doFilter(request, response);
	
	}

}
