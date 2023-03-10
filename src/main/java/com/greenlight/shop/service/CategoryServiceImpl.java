package com.greenlight.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenlight.shop.domain.Category;
import com.greenlight.shop.domain.GroupAndCategory;
import com.greenlight.shop.domain.GroupCategory;
import com.greenlight.shop.domain.Item;
import com.greenlight.shop.dto.CategoryDto;
import com.greenlight.shop.dto.GroupCategoryDto;
import com.greenlight.shop.dto.GroupSpecialCategoryDto;
import com.greenlight.shop.dto.SaleItemDto;
import com.greenlight.shop.dto.SpecialCategoryDto;
import com.greenlight.shop.repository.CategoryRepo;
import com.greenlight.shop.repository.GroupAndCategoryRepo;
import com.greenlight.shop.repository.GroupCategoryRepo;
import com.greenlight.shop.repository.ItemRepo;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	ItemRepo itemRepo;
	
	@Autowired
	GroupAndCategoryRepo groupAndCategoryRepo;
	
	@Autowired
	GroupCategoryRepo groupCategoryRepo;

	@Override
	public List<CategoryDto> getAllCategory() {
		
		return categoryRepo.findAll().stream().map(cat->{
			return new CategoryDto(cat.getCategoryId(), cat.getCategoryName(), cat.getCategoryNameMm());
		}).collect(Collectors.toList());
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {
		return categoryRepo.findById(categoryId).map(cat->{
			return new CategoryDto(cat.getCategoryId(), cat.getCategoryName(),cat.getCategoryNameMm(),cat.getSpecialId(), cat.getImg());
		}).orElse(null);
	}

	@Override
	public List<Category> getCategory() {
		List<Category> listCategory = entityManager.createQuery("SELECT c FROM Category c ORDER BY c.categoryName ASC", Category.class).getResultList();
		return listCategory;
	}

	@Transactional(readOnly = false)
	@Override
	public void saveCategory(Category cat) {
		categoryRepo.save(cat);
		
	}

	@Transactional(readOnly = false)
	@Override
	public void removeCategory(int categoryId) {
		categoryRepo.deleteById(categoryId);
	}

	@Override
	public void updateCategory(Category cat) {
		categoryRepo.save(cat);
	}
	@Override
	public List<Category> getAllCategoryOrderByName() {
		List<Category> categoryList=categoryRepo.findAllCategoryOrderByAsc();
		return categoryList;
	}

	@Override
	public List<Category> getCategoryByGroupCategoryId(int groupCategoryId) {
		List<Category> categoryList=categoryRepo.getCategoryByGroupCategoryId(groupCategoryId);
		return categoryList;
	}

	@Override
	public List<SpecialCategoryDto> getSpecialCategories() {
		List<SpecialCategoryDto> listSpecialCatDto = new ArrayList<SpecialCategoryDto>();
		List<GroupCategory> listGroupAndCat = groupCategoryRepo.findAll(PageRequest.of(0, 10)).toList();
		List<SaleItemDto> listSaleItem = new ArrayList<>();
		for(GroupCategory groupCategroy:listGroupAndCat) {
			//GroupCategory groupCategroy = groupAndCat.getGroupCategory();
			//GroupSpecialCategoryDto gsp = new GroupSpecialCategoryDto(groupCategroy.getGroupCategoryId(), groupCategroy.getGroupCategoryName(), groupCategroy.getGroupCategoryNameMm(), groupCategroy.getFrontImg());
			
			listSaleItem.add(new SaleItemDto(groupCategroy.getGroupCategoryId(), groupCategroy.getGroupCategoryName(), groupCategroy.getGroupCategoryNameMm(), groupCategroy.getFrontImg()));
//			Pageable paging = PageRequest.of(0, 10);
//			Page<Category> listCategory = categoryRepo.getCategoryByGroupCategoryId(groupCategroy.getGroupCategoryId(), paging);
//			listCategory.get().forEach(cat->{
//				listSaleItem.add(new SaleItemDto(cat.getCategoryId(), cat.getCategoryName(), cat.getCategoryNameMm(), cat.getImg()));
//			});
		}
		SpecialCategoryDto groupSpec = new SpecialCategoryDto("group", new GroupSpecialCategoryDto(), listSaleItem);
		listSpecialCatDto.add(groupSpec);
		
		List<Category> listspecialCategory = categoryRepo.getSpecialCategory();
		//groupAndCategoryService.getCategoryBy
		for(Category category:listspecialCategory) {
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
