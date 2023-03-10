package com.greenlight.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenlight.shop.repository.CategoryRepo;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	
	@Autowired
	CategoryRepo categoryRepo;


}
