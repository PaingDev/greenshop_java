package com.greenlight.shop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenlight.shop.domain.GroupCategory;
import com.greenlight.shop.dto.GroupCategoryDto;
import com.greenlight.shop.dto.SaleItemDto;
import com.greenlight.shop.dto.SpecialCategoryDto;
import com.greenlight.shop.repository.GroupCategoryRepo;

@Service
public class GroupCategorySeviceImpl implements GroupCategoryService {

	@Autowired
	GroupCategoryRepo groupCategoryRepo;

	@Override
	public List<GroupCategory> getGroupCategoryList() {
		List<GroupCategory> groupCategoryList = groupCategoryRepo.findAll();
		return groupCategoryList;
	}

	@Override
	public void saveGroupCategory(GroupCategory groupCategory) {
		groupCategoryRepo.save(groupCategory);

	}

	@Override
	public GroupCategory getGroupCategoryById(int groupCategoryId) {
		GroupCategory groupCategory = groupCategoryRepo.findById(groupCategoryId).get();
		return groupCategory;
	}

	@Override
	public void updateGroupCategory(GroupCategory groupCategory) {
		groupCategoryRepo.save(groupCategory);
	}

	@Override
	public void removeGroupCategory(int groupCategoryId) {
		groupCategoryRepo.deleteById(groupCategoryId);
		
	}

	@Override
	public List<SaleItemDto> getGroupCategories() {
		List<GroupCategory> listGroup = groupCategoryRepo.findAll();
		List<SaleItemDto> listSaleItem = listGroup.stream().map(m->{
			//int itemId, String itemName, String itemNameMm, String frontImg
			return new SaleItemDto(m.getGroupCategoryId(), m.getGroupCategoryName(), m.getGroupCategoryNameMm(), m.getFrontImg());
		}).collect(Collectors.toList());
		
		return listSaleItem;
	}
	

	

}
