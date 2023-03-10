package com.greenlight.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenlight.shop.domain.SlideImage;
import com.greenlight.shop.service.SlideImageService;

@RequestMapping("/api/v1")
@RestController
public class SlideImageRestController {

	@Autowired
	SlideImageService slideImageService;
	
	@GetMapping("/slides")
	List<SlideImage> getAllSlideImage() {
		List<SlideImage> listSideImage = slideImageService.getAllImage();
		return listSideImage;
	}

}
