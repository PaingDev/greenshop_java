package com.greenlight.shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenlight.shop.domain.Orderitemlist;

@Repository
public interface OrderItemListRepo extends JpaRepository<Orderitemlist, Integer>{
	
}
