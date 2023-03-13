package com.yash.bookstore.security.jwt;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.stereotype.Component;

import com.yash.bookstore.security.UserPrincipal;
import com.yash.bookstore.util.SecurityUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
 
public class JwtProviderImpl implements JwtProvider {

	
	@Value("${app.jwt.secret}")
	private String JWT_SECRET;
	
	@Value("${app.jwt.expiration-in-ms}")
	private Long JWT_EXPIRATION_IN_MS;
	
	@Override
	public String generateToken(UserPrincipal auth) {
		String authorities= auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining());
		
		
		return Jwts.builder()
				.setSubject(auth.getUsername())
				.claim("role", authorities)
				.claim("userId", auth.getId()) 
				.setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
				.signWith(SignatureAlgorithm.HS512,JWT_SECRET)
				.compact();
	}
	
	
	@Override
	public Authentication getAuthentication(HttpServletRequest request) {
	
	
		Claims claims = extractClaims(request);
		if(claims == null) {
			return null;
		}
		
		String username = claims.getSubject();
		
		Long userId = claims.get("userId",Long.class);
		
		Set<GrantedAuthority> authorities = Arrays.stream(claims.get("role").toString().split(","))
				.map(SecurityUtil::convertToAuthority)
				.collect(Collectors.toSet());
		
		UserDetails userdetails = UserPrincipal.builder()
				.username(username)
				.authorities(authorities)
				.id(userId)
				.build();
		
		if(username == null) {
			return null;
		}
		return new UsernamePasswordAuthenticationToken(userdetails, null,authorities);
	}
	
	@Override
	public boolean validateToken(HttpServletRequest request) {
		
		
		Claims claims = extractClaims(request);
		if (claims ==  null) {
			
			return false;
			
		}
		if(claims.getExpiration().before(new Date())) {
			return false;
		}
		return true;
		
		
	}
	
	
	
	private Claims extractClaims(HttpServletRequest request) {
		
		
		String token = SecurityUtil.extractAuthTokenRequest(request);
		if(token == null) {
			
			return null;
		}
		return Jwts.parser()
				.setSigningKey(JWT_SECRET)
				.parseClaimsJws(token)
				.getBody();
		
	}
}
