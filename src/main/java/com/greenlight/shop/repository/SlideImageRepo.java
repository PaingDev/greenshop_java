package com.greenlight.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greenlight.shop.domain.Category;
import com.greenlight.shop.domain.SlideImage;

@Repository
public interface SlideImageRepo extends JpaRepository<SlideImage, Integer>{

	@Query(value ="SELECT * FROM slideimage",nativeQuery=true)
	List<SlideImage> getAllImage();

}
