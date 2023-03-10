package com.greenlight.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

import com.greenlight.shop.domain.GroupCategory;
import com.greenlight.shop.domain.Item;
import com.greenlight.shop.dto.GroupCategoryDto;
import com.greenlight.shop.service.GroupCategoryService;
import com.greenlight.shop.util.CurrentPath;

@Controller
@RequestMapping("/admin")
public class GroupCategoryController {

	@Autowired
	GroupCategoryService groupCategoryService;

	@ModelAttribute("selectedMenu")
	public String selectedMenu() {
		return "groupCategory";
	}

	@GetMapping("/groupCategory")
	String getGroupCategory(Model model) {
		List<GroupCategory> groupCategoryList = groupCategoryService.getGroupCategoryList();
		model.addAttribute("listGroupCategory", groupCategoryList);
		return "groupCategory";
	}

	@GetMapping("/groupCategory/create")
	String getCreateGroupCategory(@ModelAttribute GroupCategoryDto groupCategoryDto) {
		groupCategoryDto.setGroupCategoryId(0);
		groupCategoryDto.setGroupCategoryName("");
		groupCategoryDto.setGroupCategoryNameMm("");
		return "createGroupCategory";
	}

	@PostMapping("/groupCategory/create")
	String postCreateGroupCategory(@ModelAttribute GroupCategoryDto groupCategoryDto) {
		groupCategoryDto.setGroupCategoryId(0);
		GroupCategory groupCategory = new GroupCategory();
		groupCategory.setGroupCategoryId(groupCategoryDto.getGroupCategoryId());
		groupCategory.setGroupCategoryName(groupCategoryDto.getGroupCategoryName());
		groupCategory.setGroupCategoryNameMm(groupCategoryDto.getGroupCategoryNameMm());
		MultipartFile fFile = groupCategoryDto.getFrontImg();
		try {
			// front Image
			if (fFile != null && !fFile.isEmpty()) {
				
				String name = fFile.getOriginalFilename();
				String ext = name.substring(name.lastIndexOf("."));
				String[] filePath = CurrentPath.generateNameAndPath("groupCat", ext);
				groupCategory.setFrontImg(filePath[0]);
				fFile.transferTo(new File(filePath[1]));
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		groupCategoryService.saveGroupCategory(groupCategory);
		return "redirect:/admin/groupCategory/"+groupCategory.getGroupCategoryId()+"/edit";
	}

	@GetMapping("/groupCategory/{groupCategoryId}/edit")
	String editGroupCategory(@ModelAttribute("groupCategoryDto") GroupCategoryDto groupCategoryDto,
			@PathVariable("groupCategoryId") int groupCategoryId, Model model) {
		if (groupCategoryDto.getGroupCategoryId() == 0) {
			return "redirect:/admin/groupCategory";
		}
		GroupCategory groupCategory = groupCategoryService.getGroupCategoryById(groupCategoryId);
		groupCategoryDto.setGroupCategoryName(groupCategory.getGroupCategoryName());
		groupCategoryDto.setGroupCategoryNameMm(groupCategory.getGroupCategoryNameMm());
		
		if (groupCategory != null) {
			model.addAttribute("groupCategory", groupCategory);
		}
		return "createGroupCategory";
	}
	
	@PostMapping("/groupCategory/{groupCategoryId}/edit")
	String postEditGroupCategory(@ModelAttribute("groupCategoryDto") GroupCategoryDto groupCategoryDto,
			@PathVariable("groupCategoryId") int groupCategoryId) {
		GroupCategory groupCategory = groupCategoryService.getGroupCategoryById(groupCategoryId);
		groupCategory.setGroupCategoryId(groupCategoryDto.getGroupCategoryId());
		groupCategory.setGroupCategoryName(groupCategoryDto.getGroupCategoryName());
		groupCategory.setGroupCategoryNameMm(groupCategoryDto.getGroupCategoryNameMm());
		MultipartFile fFile = groupCategoryDto.getFrontImg();
		try {
			// front Image
			if (fFile != null && !fFile.isEmpty()) {
				File file = new File(CurrentPath.userDirectory + "/items/" + groupCategory.getFrontImg());
				file.delete();
				
				String name = fFile.getOriginalFilename();
				String ext = name.substring(name.lastIndexOf("."));
				String[] filePath = CurrentPath.generateNameAndPath("groupCat", ext);
				groupCategory.setFrontImg(filePath[0]);
				fFile.transferTo(new File(filePath[1]));
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		groupCategoryService.saveGroupCategory(groupCategory);
		return "redirect:/admin/groupCategory/"+groupCategory.getGroupCategoryId()+"/edit";
	}
	
	@PostMapping("/groupCategory/{groupCategoryId}/delete")
	String deleteGroupCategory(@PathVariable("groupCategoryId") int groupCategoryId) {
		if(groupCategoryId == 0) {
			return "redirect:/admin/groupCategory";
		}
		groupCategoryService.removeGroupCategory(groupCategoryId);
		return "redirect:/admin/groupCategory";
	}
	
	@PostMapping("/groupCategory/{groupCategoryId}/image/delete")
	String deleteItemImage(@PathVariable("groupCategoryId") int groupCategoryId, @RequestParam(value = "img") String img) {
		System.out.println("Delete Image");
		GroupCategory groupCategory = groupCategoryService.getGroupCategoryById(groupCategoryId);
		if (groupCategory != null) {

			if ("front".equals(img)) {
				File file = new File(CurrentPath.userDirectory + "/items/" + groupCategory.getFrontImg());
				file.delete();
				groupCategory.setFrontImg(null);
				groupCategoryService.saveGroupCategory(groupCategory);
			}
		}
		return "redirect:/admin/groupCategory/" + groupCategoryId + "/edit";
	}
}
