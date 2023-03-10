package com.greenlight.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenlight.shop.dto.GroupCategoryDto;
import com.greenlight.shop.dto.SaleItemDto;
import com.greenlight.shop.dto.SpecialCategoryDto;
import com.greenlight.shop.service.GroupAndCategoryService;
import com.greenlight.shop.service.GroupCategoryService;

@RestController
@RequestMapping("/api/v1")
public class GroupCategoryRestController {
	@Autowired
	GroupAndCategoryService groupAndCategoryService;
	
	@Autowired
	GroupCategoryService groupCategoryService;

	@GetMapping("/groupCategory/{id}")
	public List<SpecialCategoryDto> getSpecialCategories(@PathVariable("id") int groupCategoryId){
		List<SpecialCategoryDto> listCategoryDto = groupAndCategoryService.getSpecialCategoriesByGroupCategoryId(groupCategoryId);
		return listCategoryDto;
	}
	
	@GetMapping("/groupCategory")
	public List<SaleItemDto> getGroupCategories(){
		List<SaleItemDto> listCategoryDto = groupCategoryService.getGroupCategories();
		return listCategoryDto;
	}
}
