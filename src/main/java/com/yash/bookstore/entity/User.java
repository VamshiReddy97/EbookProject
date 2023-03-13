package com.yash.bookstore.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username",unique= true,nullable = false,length = 100)
	private String username;
	
	@Column(name = "password",nullable = false,length = 100)
	private String password;
	
	@Column(name = "name",nullable = false,length = 100)
	private String name;
	
	@Column(name = "create_time",nullable = false)
	private LocalDateTime createTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "roles", nullable = false)
	private Role roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public Role getRoles() {
		return roles;
	}

	public void setRole(Role role) {
		this.roles = roles;
	}
	
	@Transient
	private String token;
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
	
}
