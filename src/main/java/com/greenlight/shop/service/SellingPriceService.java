package com.greenlight.shop.service;

import java.util.List;


import com.greenlight.shop.domain.Sellingprice;

public interface SellingPriceService {

	List<Sellingprice> getPriceListByItemId(int itemId);

	int getSellingPriceByItemAndUnitId(int itemId, int unitId);

	void deleteSellingPriceByItemAndUnitId(int itemId, int unitId);


}
