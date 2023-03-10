package com.greenlight.shop.dto;

import javax.validation.constraints.Min;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
	
	@Min(value = 1, message = "Id must not zero")
	int categoryId;
	String categoryName;
	String categoryNameMm;
	int specialId;
	
	String img;
	
	MultipartFile imagePart;
	
	public CategoryDto(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public CategoryDto(int categoryId, String categoryName,
			String categoryNameMm) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryNameMm = categoryNameMm;
	}

	public CategoryDto(int categoryId, String categoryName,
			String categoryNameMm, int specialId) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryNameMm = categoryNameMm;
		this.specialId = specialId;
	}

	public CategoryDto(@Min(value = 1, message = "Id must not zero") int categoryId, String categoryName,
			String categoryNameMm, int specialId, String img) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryNameMm = categoryNameMm;
		this.specialId = specialId;
		this.img = img;
	}

	
	
	
}
