package com.greenlight.shop.service;

import java.util.List;

import com.greenlight.shop.domain.GroupCategory;
import com.greenlight.shop.dto.GroupCategoryDto;
import com.greenlight.shop.dto.SaleItemDto;
import com.greenlight.shop.dto.SpecialCategoryDto;

public interface GroupCategoryService {
	List<GroupCategory> getGroupCategoryList();

	void saveGroupCategory(GroupCategory groupCategory);

	GroupCategory getGroupCategoryById(int groupCategoryId);

	void updateGroupCategory(GroupCategory groupCategory);

	void removeGroupCategory(int groupCategoryId);

	List<SaleItemDto> getGroupCategories();

}
