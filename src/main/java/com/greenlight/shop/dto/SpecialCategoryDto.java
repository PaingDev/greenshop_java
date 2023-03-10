package com.greenlight.shop.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialCategoryDto {
	
	private String title;
	private GroupSpecialCategoryDto groupCategoryDto;
	private CategoryDto categoryDto;
	private List<SaleItemDto> listSaleItemDto;
	public SpecialCategoryDto(String title, CategoryDto categoryDto, List<SaleItemDto> listSaleItemDto) {
		super();
		this.title = title;
		this.categoryDto = categoryDto;
		this.listSaleItemDto = listSaleItemDto;
	}
	public SpecialCategoryDto(String title, GroupSpecialCategoryDto groupCategoryDto, List<SaleItemDto> listSaleItemDto) {
		super();
		this.title = title;
		this.groupCategoryDto = groupCategoryDto;
		this.listSaleItemDto = listSaleItemDto;
	}

}
