package com.yash.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.bookstore.entity.PurchaseHistory;
import com.yash.bookstore.security.UserPrincipal;
import com.yash.bookstore.service.PurchaseHistoryService;

@RestController
@RequestMapping("api/purchase-history")
public class PurchaseHistoryController {

	@Autowired
	private PurchaseHistoryService purchaseHistoryService;

	@PostMapping //api/purchase-history
	public ResponseEntity<?> savePurchaseHistory(@RequestBody PurchaseHistory purchaseHistory)
	{
		return new ResponseEntity<>(purchaseHistoryService.savePurchaseHistory(purchaseHistory),HttpStatus.CREATED);
	}

	@GetMapping //api/purchase-history
	public ResponseEntity<?> getAllPurchaseOfUser(@AuthenticationPrincipal UserPrincipal userPrincipal){
	
		return ResponseEntity.ok(purchaseHistoryService.findPurchasedItemOfUser(userPrincipal.getId()));
	}
	
}
