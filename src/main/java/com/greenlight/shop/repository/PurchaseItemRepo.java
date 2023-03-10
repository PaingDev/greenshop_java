package com.greenlight.shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenlight.shop.domain.Purchaseitem;

@Repository
public interface PurchaseItemRepo extends JpaRepository<Purchaseitem, Integer>{
	
}
