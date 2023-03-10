package com.greenlight.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenlight.shop.dto.SaleItemDto;
import com.greenlight.shop.service.ItemService;

@RestController
@RequestMapping("/api/v1")
public class ItemRestController {
	
	@Autowired
	ItemService itemService;

	@GetMapping("/items")
	public List<SaleItemDto> getAllItem(@RequestParam(value = "page", defaultValue = "0")int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "1") int pageSize,
			@RequestParam (value="sortBy",required=false)String sortBy,
			@RequestParam (value="dir",defaultValue="ASC",required=false)String dir) {
		List<SaleItemDto> listItem = itemService.getListSaleItem(pageNo, pageSize,sortBy,dir);
		return listItem;
	}
	
	@GetMapping("/item")
	List<SaleItemDto> searchItem(@RequestParam(value="itemName") String itemName,
			@RequestParam (value="sortBy",required=false)String sortBy,
			@RequestParam (value="dir",defaultValue="ASC",required=false)String dir) {
		List<SaleItemDto> itemList=itemService.searchItem(itemName, sortBy,dir);
		return itemList;
	}
	
}
