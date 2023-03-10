package com.greenlight.shop.service;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;

import com.greenlight.shop.domain.Item;
import com.greenlight.shop.domain.Sellingprice;
import com.greenlight.shop.dto.SaleItemDto;
import com.greenlight.shop.dto.SellingItemDto;

public interface ItemService {

	List<SaleItemDto> getListSaleItem();

	List<SaleItemDto> getItemByCategoryId(int categoryId, int pageNo, int pageSize,String ts,String itemName);

	List<SaleItemDto> getListSaleItem(int pageNo, int pageSize, String sortBy, String dir);

	List<Item> getItemList();
	
	void saveItem(Item item);

	Item getItemById(int itemId);
	
	void removeItem(int itemId);

	List<SaleItemDto> searchItem(String itemName, String sortBy, String dir);

	void saveSellingPrice(Sellingprice sellingPrice);

	List<Item> getItemListByCategoryId(int categoryId);

	List<Item> getAllItemList();

	void hideItem(int itemId, boolean hide);

	Page<SellingItemDto> getItemList(String text, int categoryId, int page);

	Page<SellingItemDto> getUnlistedItemList(String text, int catId, int page);

	
}
