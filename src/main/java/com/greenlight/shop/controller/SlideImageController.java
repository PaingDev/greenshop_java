package com.greenlight.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.greenlight.shop.domain.SlideImage;
import com.greenlight.shop.dto.BasicOrderItem;
import com.greenlight.shop.service.SlideImageService;
import com.greenlight.shop.util.CurrentPath;

@RequestMapping("/admin")
@Controller
public class SlideImageController {

	@Autowired
	SlideImageService slideImageService;

	@ModelAttribute("selectedMenu")
	public String selectedMenu() {
		return "slideImage";
	}

	@GetMapping("/slideImage")
	public String sideImagePage(Model model) {
		List<SlideImage> listSideImage = slideImageService.getAllImage();
		model.addAttribute("listSlideImage", listSideImage);
		return "slideImage";
	}

	@GetMapping("/slideImage/create")
	public String createImage(Model model, @ModelAttribute SlideImage slideImage) {

		return "createSlideImage";
	}

	@PostMapping("/slideImage/create")
	public String createSlideImage(Model model, @ModelAttribute SlideImage slideImage) {
		try {
			MultipartFile fFile = slideImage.getImagePart();
			String name = fFile.getOriginalFilename();
			String ext = name.substring(name.lastIndexOf("."));
			String[] filePath = CurrentPath.generateNameAndPath("slide", ext);

			slideImage.setImg(filePath[0]);

			fFile.transferTo(new File(filePath[1]));
			slideImageService.updateSlideImage(slideImage);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/admin/slideImage";
	}

	@PostMapping("/slideImage/{slideImageId}/edit")
	public String createSlideImage(@PathVariable("slideImageId") int slideImageId,
			@ModelAttribute SlideImage slideImage, BindingResult bind, Model model) {
		System.out.println("Slide Image Post" + slideImage.getImagePart());
		slideImage.setSlideImageId(slideImageId);
		if ("update".equals(slideImage.getAction())) {

			try {
				MultipartFile fFile = slideImage.getImagePart();
				String name = fFile.getOriginalFilename();
				String ext = name.substring(name.lastIndexOf("."));
				String[] filePath = CurrentPath.generateNameAndPath("slide", ext);

				slideImage.setImg(filePath[0]);

				fFile.transferTo(new File(filePath[1]));

				slideImageService.updateSlideImage(slideImage);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "redirect:/admin/slideImage";
		} else if ("delete".equals(slideImage.getAction())) {
			System.out.println("Delete Image");
			slideImageService.deleteSlideImage(slideImage.getSlideImageId());

		}
		return "redirect:/admin/slideImage";
	}

}
