package com.greenlight.shop.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greenlight.shop.domain.Orderitem;
import com.greenlight.shop.dto.OrderDetail;
import com.greenlight.shop.dto.OrderDto;

@Repository
public interface OrderItemRepo extends JpaRepository<Orderitem, Integer>{

	@Query(value = "SELECT o.orderItemId, t.townshipName, o.address, o.phoneNo, o.preferedTime, o.orderDate, oi.itemName, oi.unitPrice,oi.qty, oi.amount, u.unitName FROM " + 
			"orderitemlist oi LEFT JOIN " + 
			"orderitem o ON oi.orderItemId=o.orderItemId " + 
			"JOIN township t ON o.townshipId=t.townshipId " + 
			"JOIN sellingprice sp ON sp.priceId=oi.priceId " + 
			"JOIN unit u ON sp.unitId=u.unitId WHERE o.orderDate BETWEEN ?1 AND ?2",nativeQuery = true)
	List<Object[]> getOrderDetailList(Date fromDate, Date toDate);

	@Query("SELECT oi FROM Orderitem oi WHERE oi.useraccount.userAccountId=?1 AND oi.status<>'REJECT'")
	List<Orderitem> getOrderListByUserId(int userAccountId);
	
	
	
}
