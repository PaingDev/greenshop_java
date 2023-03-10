package com.greenlight.shop.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicOrderItem {
	
	int itemId;
	@NotEmpty(message = "ItemName is blank.")
	@NotNull(message = "ItemName is blank.")
	String itemName;
	
	@NotEmpty(message = "ItemName MM is blank.")
	@NotNull(message = "ItemName MM is blank.")
	String itemNameMm;
	
	@Min(value = 1,message = "Category is blank.")
	int categoryId;
	
	int discountPrice;
	
	MultipartFile frontImg;
	
	MultipartFile backImg;

}
