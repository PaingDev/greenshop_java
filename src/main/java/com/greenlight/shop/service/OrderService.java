package com.greenlight.shop.service;

import java.util.Date;
import java.util.List;

import com.greenlight.shop.domain.Orderitem;
import com.greenlight.shop.domain.Orderitemlist;
import com.greenlight.shop.dto.OrderDetail;
import com.greenlight.shop.dto.OrderDto;

public interface OrderService {

	String order(OrderDto orderDto);

	List<OrderDetail> getOrderByDate(Date fromDate, Date toDate);

	List<Orderitem> getOrderListByDate(Date fromDate, Date toDate);

	List<OrderDto> getOrderListByUserId(int userAccountId);

	void changeStatus(int orderItemId, String status);

	Orderitem getOrderById(int orderId);

}
