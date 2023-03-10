package com.greenlight.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupSpecialCategoryDto {
	
	int groupCategoryId;

	String groupCategoryName;
	
	String groupCategoryNameMm;
	
	String frontImg;

}
