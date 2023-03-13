package com.yash.bookstore.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.bookstore.entity.PurchaseHistory;
import com.yash.bookstore.repository.PurchaseHistoryRepository;
import com.yash.bookstore.repository.projection.PurchaseItem;

@Service
@Transactional
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {

	@Autowired
	private PurchaseHistoryRepository purchaseHistoryRepository;


	@Override
	public PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory) {
		purchaseHistory.setPurchaseTime(LocalDateTime.now());
		
		return purchaseHistoryRepository.save(purchaseHistory);
	}

	@Override
	public List<PurchaseItem> findPurchasedItemOfUser(Long userId){
		
		
		return purchaseHistoryRepository.findAllPurchaseOfUser(userId);
	}
	
	
}
