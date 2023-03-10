package com.greenlight.shop.service;

import java.util.List;

import com.greenlight.shop.domain.GroupAndCategory;
import com.greenlight.shop.dto.GroupCategoryDto;
import com.greenlight.shop.dto.SpecialCategoryDto;


public interface GroupAndCategoryService {

	
     void removeGroupAndCategory(int categoryId,int groupCategoryId);

	void saveGroupAndCategory(GroupAndCategory groupAndCategory);

	List<SpecialCategoryDto> getSpecialCategoriesByGroupCategoryId(int groupCategoryId);


	
}
