package com.greenlight.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenlight.shop.dto.CategoryDto;
import com.greenlight.shop.dto.SaleItemDto;
import com.greenlight.shop.dto.SpecialCategoryDto;
import com.greenlight.shop.service.CategoryService;
import com.greenlight.shop.service.ItemService;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ItemService itemService;
	
	
	@GetMapping("/categories")
	public List<CategoryDto> getAllCategory() {
		List<CategoryDto> listCategory = new ArrayList<CategoryDto>();
		listCategory = categoryService.getAllCategory();
		return listCategory;
	}
	
	@GetMapping("/categories/{categoryId}")
	public CategoryDto getCategoryById(@PathVariable("categoryId") int categoryId) {
		CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
		return categoryDto;
	}
	
	@GetMapping("/specialCategories")
	public List<SpecialCategoryDto> getSpecialCategories(){
		List<SpecialCategoryDto> listCategoryDto = categoryService.getSpecialCategories();
		return listCategoryDto;
	}
	
	@GetMapping("/categories/{categoryId}/items")
	public List<SaleItemDto> getItemByCategoryId(@PathVariable("categoryId") int categoryId, 
			@RequestParam(value = "page", defaultValue = "0")int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = "1") int pageSize,
			@RequestParam (value="sortBy",required=false)String sortBy,
			@RequestParam (value="dir",defaultValue="ASC",required=false)String dir){		
		List<SaleItemDto> listItem = itemService.getItemByCategoryId(categoryId, pageNo, pageSize, sortBy ,dir);
		return listItem;
	}
	
}
