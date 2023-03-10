package com.greenlight.shop.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greenlight.shop.domain.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{

	@Query(value ="SELECT * FROM category ORDER BY categoryName ASC ",nativeQuery=true)
	List<Category> findAllCategoryOrderByAsc();

	@Query(value ="SELECT gac.category FROM  GroupAndCategory gac where gac.groupCategory.groupCategoryId=?1 ORDER BY gac.category.categoryName ASC")
	List<Category> getCategoryByGroupCategoryId(int groupCategoryId);

	@Query(value ="SELECT c FROM Category c WHERE c.specialId>0 ORDER BY c.specialId ASC ")
	List<Category> getSpecialCategory();
	
	@Query(value ="SELECT gac.category FROM  GroupAndCategory gac where gac.groupCategory.groupCategoryId=?1 ORDER BY gac.category.categoryName ASC")
	Page<Category> getCategoryByGroupCategoryId(int groupCategoryId, Pageable paging);
	
}
