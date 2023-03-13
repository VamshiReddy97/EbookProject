package com.yash.bookstore.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yash.bookstore.entity.Role;
import com.yash.bookstore.entity.User;
import com.yash.bookstore.util.SecurityUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

	private Long id;
	private String username;
	transient private String password;  // no need for serialization
	
	transient private User user; // no use if using jwt
	
	private Set<GrantedAuthority> authorities;
	
public static UserPrincipal createSuperUser() {
	
	Set<GrantedAuthority> authorities = Set.of(SecurityUtil.convertToAuthority(Role.MANAGER.name()));
	
	
	return UserPrincipal.builder()
			.id(-1L)
			.username("system-admin")
			.authorities(authorities)
			.build();
}	
	
	/*
	 * public UserPrincipal() { super(); // TODO Auto-generated constructor stub }
	 * 
	 * public UserPrincipal(Long id, String username, String password, User user,
	 * Set<GrantedAuthority> authorities) { super(); this.id = id; this.username =
	 * username; this.password = password; this.user = user; this.authorities =
	 * authorities; }
	 */

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
