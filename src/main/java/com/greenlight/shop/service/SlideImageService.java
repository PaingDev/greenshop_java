package com.greenlight.shop.service;

import java.util.List;

import com.greenlight.shop.domain.SlideImage;

public interface SlideImageService {

	List<SlideImage> getAllImage();

	void updateSlideImage(SlideImage slideImage);

	void deleteSlideImage(int slideImageId);

}
