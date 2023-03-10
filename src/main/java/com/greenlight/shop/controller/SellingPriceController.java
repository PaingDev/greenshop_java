package com.greenlight.shop.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenlight.shop.domain.Item;
import com.greenlight.shop.domain.Sellingprice;
import com.greenlight.shop.domain.Unit;
import com.greenlight.shop.dto.SellingPriceDto;
import com.greenlight.shop.service.ItemService;
import com.greenlight.shop.service.SellingPriceService;
import com.greenlight.shop.service.UnitService;


@Controller
@RequestMapping("/admin")
public class SellingPriceController {
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	UnitService unitService;
	
	@Autowired 
	SellingPriceService sellingPriceService;
	
	@GetMapping("/sellingItem/create")
	String createSellingItem(@ModelAttribute("sellingPriceDto") SellingPriceDto sellingPriceDto, Model model) {
		List<Item> listItem = itemService.getAllItemList();
		List<Unit> listUnit = unitService.getUnitList();
		if(sellingPriceDto.getItemId()!=0 && sellingPriceDto.getUnitId()!=0) {
			int sellingPrice = sellingPriceService.getSellingPriceByItemAndUnitId(sellingPriceDto.getItemId(), sellingPriceDto.getUnitId());
			sellingPriceDto.setPrice(sellingPrice);
		}
		
		model.addAttribute("listItem", listItem);
		model.addAttribute("listUnit", listUnit);
		
		return "createSellingItem";
	}
	
	@GetMapping("/sellingItem/remove")
	String removeSellingItem(@ModelAttribute("sellingPriceDto") SellingPriceDto sellingPriceDto, Model model) {
		if(sellingPriceDto.getItemId()!=0 && sellingPriceDto.getUnitId()!=0) {
			sellingPriceService.deleteSellingPriceByItemAndUnitId(sellingPriceDto.getItemId(), sellingPriceDto.getUnitId());
		}
		return "redirect:/admin/sellingItem/create?itemId="+sellingPriceDto.getItemId()+ "&unitId="+sellingPriceDto.getUnitId();
	}
	
	@PostMapping("/sellingItem/create")
	String postCreateSellingItem(@ModelAttribute("sellingPriceDto") SellingPriceDto sellingPriceDto, Model model) {
		List<Item> listItem = itemService.getAllItemList();
		List<Unit> listUnit = unitService.getUnitList();
		model.addAttribute("listItem", listItem);
		model.addAttribute("listUnit", listUnit);
		Sellingprice sellingPrice = new Sellingprice();
		sellingPrice.setDate(new Date());
		sellingPrice.setItem(new Item(sellingPriceDto.getItemId()));
		sellingPrice.setUnit(new Unit(sellingPriceDto.getUnitId()));
		sellingPrice.setUnitPrice(sellingPriceDto.getPrice());
		itemService.saveSellingPrice(sellingPrice);
		
		return "redirect:/admin/itemAndPrice";
	}
}
