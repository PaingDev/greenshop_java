package com.greenlight.shop.service;

import java.util.List;

import com.greenlight.shop.domain.Category;
import com.greenlight.shop.dto.CategoryDto;
import com.greenlight.shop.dto.SpecialCategoryDto;

public interface CategoryService {

	List<CategoryDto> getAllCategory();

	CategoryDto getCategoryById(int categoryId);

	List<Category> getCategory();

	void saveCategory(Category cat);

	void removeCategory(int categoryId);

	void updateCategory(Category cat);

    List<Category> getAllCategoryOrderByName();

	List<Category> getCategoryByGroupCategoryId(int groupCategoryId);

	List<SpecialCategoryDto> getSpecialCategories();
}
