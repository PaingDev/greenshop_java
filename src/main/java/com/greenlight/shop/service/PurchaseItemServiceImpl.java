package com.greenlight.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenlight.shop.repository.CategoryRepo;

@Service
public class PurchaseItemServiceImpl implements PurchaseItemService{
	
	@Autowired
	CategoryRepo categoryRepo;


}
