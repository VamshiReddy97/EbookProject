package com.yash.bookstore.service;

import java.util.List;

import com.yash.bookstore.entity.PurchaseHistory;
import com.yash.bookstore.repository.projection.PurchaseItem;

public interface PurchaseHistoryService {

	PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory);

	List<PurchaseItem> findPurchasedItemOfUser(Long userId);

}
