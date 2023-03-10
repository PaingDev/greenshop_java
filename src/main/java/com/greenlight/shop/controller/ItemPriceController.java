package com.greenlight.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenlight.shop.domain.Item;
import com.greenlight.shop.dto.SaleItemDto;
import com.greenlight.shop.service.ItemService;

@Controller
@RequestMapping("/admin")
public class ItemPriceController {
	@Autowired
	ItemService itemService;
	
	@ModelAttribute("selectedMenu")
	public String selectedMenu() {
		return "itemAndPrice";
	}
	
	@GetMapping("/itemAndPrice")
	String itemAndPrice(Model model) {
		List<SaleItemDto> listSaleItem = itemService.getListSaleItem();
		model.addAttribute("listSaleItem", listSaleItem);
		return "itemAndPrice";
	}
	
	@GetMapping("/itemlist")
	String itemList(Model model) {
		List<Item> listItem = itemService.getAllItemList();
		model.addAttribute("items", listItem);
		return "itemlist";
	}

}
