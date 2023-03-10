package com.greenlight.shop.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
	
	List<OrderItemDto> listDto;
	
	int townshipId;
	String orderId;
	String townshipName;
	String address;
	String phoneNo;
	String preferedTime;
	int deliveryCharge;
	@JsonFormat(pattern = "dd-MMM-yyyy")
	Date date;
	
	String itemDetail;
	int total;
	
	

}
