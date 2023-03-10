package com.greenlight.shop.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenlight.shop.domain.Category;
import com.greenlight.shop.domain.GroupAndCategory;
import com.greenlight.shop.dto.CategoryDto;
import com.greenlight.shop.dto.SaleItemDto;
import com.greenlight.shop.dto.SpecialCategoryDto;
import com.greenlight.shop.repository.GroupAndCategoryRepo;
import com.greenlight.shop.repository.ItemRepo;

@Service
public class GroupAndCategoryServiceImpl implements GroupAndCategoryService {
	
	@Autowired
	GroupAndCategoryRepo groupAndCategoryRepo;
	
	@Autowired
	ItemRepo itemRepo;

	@Transactional(readOnly=false)
	@Override
	public void removeGroupAndCategory(int categoryId,int groupCategoryId) {
		groupAndCategoryRepo.deleteByCategoryIdAndGroupCategoryId(categoryId,groupCategoryId);	
	}


	@Override
	public void saveGroupAndCategory(GroupAndCategory groupAndCategory) throws DataIntegrityViolationException {
		groupAndCategoryRepo.save(groupAndCategory);
	}


	@Override
	public List<SpecialCategoryDto> getSpecialCategoriesByGroupCategoryId(int groupCategoryId) {
		List<SpecialCategoryDto> listSpecialCatDto = new ArrayList<SpecialCategoryDto>();
		
		List<Category> listCategory = groupAndCategoryRepo.getCategoryByGroupCategoryId(groupCategoryId);
		for(Category category:listCategory) {
			List<SaleItemDto> listSaleItemDto = new ArrayList<>();
			Pageable paging = PageRequest.of(0, 10, Sort.by(Direction.ASC, "date"));
			Page<SaleItemDto> page = itemRepo.getItemByCategoryId(category.getCategoryId(), paging);
			if(page.hasContent()) {
				listSaleItemDto = page.getContent();
			}
			SpecialCategoryDto specialCat = new SpecialCategoryDto("category", new CategoryDto(category.getCategoryId(), category.getCategoryName(), category.getCategoryNameMm(), category.getSpecialId()), listSaleItemDto);
			
			
			listSpecialCatDto.add(specialCat);
		}
		
		return listSpecialCatDto;
	}

	
	
	
		
	

}
