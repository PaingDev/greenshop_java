package com.greenlight.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greenlight.shop.domain.GroupCategory;

@Repository
public interface GroupCategoryRepo extends JpaRepository<GroupCategory,Integer> {
	
	@Query(value = "SELECT gc FROM GroupCategory gc")
	Page<GroupCategory> findAll(Pageable paging);

}
