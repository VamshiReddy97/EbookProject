package com.yash.bookstore.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name="purchase_history")
public class PurchaseHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_id",nullable = false)
	private Long userId;
	
	@Column(name = "book_id",nullable = false)
	private Long bookId;
	
	@Column(name = "price",nullable = false)
	private Double price;
	
	@Column(name = "purchase_time",nullable = false)
	private LocalDateTime purchaseTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDateTime getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(LocalDateTime purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	
	
}
