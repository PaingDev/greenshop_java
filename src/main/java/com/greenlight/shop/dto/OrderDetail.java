package com.greenlight.shop.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetail {
	
	int orderId;
	String townshipName;
	String address;
	String phoneNo;
	String preferedTime;
	Date date;
	String itemDetail;
	int total;
	int qty;
	int amount;
	
	
	public OrderDetail(int orderId, String townshipName, String address, String phoneNo, String preferedTime, Date date,
			String itemDetail, int total) {
		super();
		this.orderId = orderId;
		this.townshipName = townshipName;
		this.address = address;
		this.phoneNo = phoneNo;
		this.preferedTime = preferedTime;
		this.date = date;
		this.itemDetail = itemDetail;
		this.total = total;
	}


	public OrderDetail(int orderId, String townshipName, String address, String phoneNo, String preferedTime, Date date,
			String itemDetail) {
		super();
		this.orderId = orderId;
		this.townshipName = townshipName;
		this.address = address;
		this.phoneNo = phoneNo;
		this.preferedTime = preferedTime;
		this.date = date;
		this.itemDetail = itemDetail;
	}
	
	
}
