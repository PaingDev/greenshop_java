package com.greenlight.shop.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenlight.shop.domain.SlideImage;
import com.greenlight.shop.repository.SlideImageRepo;
import com.greenlight.shop.util.CurrentPath;

@Service
public class SlideImageServiceImpl implements SlideImageService {
	
	@Autowired
	SlideImageRepo slideImageRepo;

	@Transactional(readOnly = true)
	@Override
	public List<SlideImage> getAllImage() {
		List<SlideImage> listSlideImage = slideImageRepo.getAllImage();
		return listSlideImage;
	}

	@Override
	public void updateSlideImage(SlideImage slideImage) {
		slideImageRepo.save(slideImage);
	}

	@Override
	public void deleteSlideImage(int slideImageId) {
		slideImageRepo.findById(slideImageId).ifPresent(slideImg->{
			
			try {
				Files.deleteIfExists(Paths.get(CurrentPath.userDirectory+"/items/"+ slideImg.getImg()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			slideImg.getImg();
			slideImageRepo.delete(slideImg);
		});
		
	}

}
