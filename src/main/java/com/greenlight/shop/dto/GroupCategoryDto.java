package com.greenlight.shop.dto;


import org.springframework.web.multipart.MultipartFile;

import com.greenlight.shop.domain.GroupCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupCategoryDto {

//	@Min(value = 1, message = "Id must not zero")
	int groupCategoryId;

	String groupCategoryName;
	
	String groupCategoryNameMm;
	
	MultipartFile frontImg;

	public GroupCategoryDto( int groupCategoryId,String groupCategoryName) {
		super();
		this.groupCategoryId = groupCategoryId;
		this.groupCategoryName = groupCategoryName;
	}

}
