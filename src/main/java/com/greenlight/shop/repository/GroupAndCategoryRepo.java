package com.greenlight.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greenlight.shop.domain.Category;
import com.greenlight.shop.domain.GroupAndCategory;

@Repository
public interface GroupAndCategoryRepo extends JpaRepository<GroupAndCategory,Integer> {

	@Modifying
	@Query(value="DELETE FROM groupAndCategory WHERE categoryId=?1 AND groupCategoryId=?2",nativeQuery=true)
	void deleteByCategoryIdAndGroupCategoryId(int categoryId ,int groupCategoryId);

	@Query("SELECT gc.category FROM GroupAndCategory gc WHERE gc.groupCategory.groupCategoryId=?1")
	List<Category> getCategoryByGroupCategoryId(int groupCategoryId);

    
	

}
