package com.greenlight.shop.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenlight.shop.domain.Orderitem;
import com.greenlight.shop.domain.Useraccount;
import com.greenlight.shop.dto.OrderDto;
import com.greenlight.shop.dto.TokenData;
import com.greenlight.shop.service.OrderService;

@RestController
@RequestMapping("/api/v1")
public class OrderRestController {
	
	Logger logger = LoggerFactory.getLogger(OrderRestController.class);
	
	@Autowired
	OrderService orderService;
	
	@RolesAllowed(value= {"USER"})
	@PostMapping("/orders")
	public String order(@RequestBody OrderDto orderDto) {
		logger.debug(orderDto.toString());
		return orderService.order(orderDto);
	}

	@RolesAllowed(value= {"USER"})
	@GetMapping("/orders")
	public List<OrderDto> getOrder() {
		Useraccount data = (Useraccount) SecurityContextHolder.getContext().getAuthentication().getDetails();
		List<OrderDto> listOrder = new ArrayList<OrderDto>();
		listOrder = orderService.getOrderListByUserId(data.getUserAccountId());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat of = new SimpleDateFormat("hh:mm a");
		for(OrderDto item:listOrder) {
			String time = item.getPreferedTime();
			int len = time.split(":").length;
			if(len != 4) {
				
				try {
					Date date = sf.parse(time);
					time = of.format(date);
					item.setPreferedTime(time);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		return listOrder;
	}
	
}
