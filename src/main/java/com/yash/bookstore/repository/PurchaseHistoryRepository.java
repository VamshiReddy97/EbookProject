package com.yash.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yash.bookstore.entity.PurchaseHistory;
import com.yash.bookstore.repository.projection.PurchaseItem;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {

	@Query("select" + " b.title as title, ph.price as price, ph.purchaseTime as purchaseTime" +
	" from PurchaseHistory ph left join Book b on b.id = ph.bookId" + 
	" where ph.userId =:userId")
	List<PurchaseItem> findAllPurchaseOfUser(@Param("userId") Long userId);
}
