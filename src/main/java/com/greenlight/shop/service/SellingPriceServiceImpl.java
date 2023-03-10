package com.greenlight.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenlight.shop.domain.Sellingprice;
import com.greenlight.shop.repository.CategoryRepo;
import com.greenlight.shop.repository.SellingPriceRepo;

@Service
public class SellingPriceServiceImpl implements SellingPriceService{
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	SellingPriceRepo sellingPriceRepo;

	@Override
	public List<Sellingprice> getPriceListByItemId(int itemId) {
		List<Sellingprice> listSellingPrice = sellingPriceRepo.getSellingPriceByItemId(itemId);
		return listSellingPrice;
	}

	@Transactional(readOnly = true)
	@Override
	public int getSellingPriceByItemAndUnitId(int itemId, int unitId) {
		Sellingprice sellingPrice = sellingPriceRepo.getSellingPriceByItemAndUnitId(itemId, unitId);
		if(sellingPrice == null) {
			return 0;
		}
		return sellingPrice.getUnitPrice();
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteSellingPriceByItemAndUnitId(int itemId, int unitId) {
		sellingPriceRepo.deleteSellingPriceByItemAndUnitId(itemId, unitId);
		
	}


}
