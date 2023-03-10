package com.greenlight.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greenlight.shop.domain.Category;
import com.greenlight.shop.domain.GroupAndCategory;
import com.greenlight.shop.domain.GroupCategory;
import com.greenlight.shop.dto.GroupAndCategoryDto;
import com.greenlight.shop.service.CategoryService;
import com.greenlight.shop.service.GroupAndCategoryService;
import com.greenlight.shop.service.GroupCategoryService;

@Controller
@RequestMapping("/admin")
public class GroupAndCategoryController {

	@Autowired
	GroupAndCategoryService groupAndCategoryService;

	@Autowired
	CategoryService categorySevice;

	@Autowired
	GroupCategoryService groupCategoryService;

	@ModelAttribute("selectedMenu")
	public String selectedMenu() {
		return "groupAndCategory";
	}

	@GetMapping("/groupAndCategory")
	String getGroupAndCategory(@ModelAttribute GroupAndCategoryDto groupAndCategoryDto,
			Model model) {
		List<Category> categoryList = categorySevice.getAllCategoryOrderByName();
		model.addAttribute("listCategory", categoryList);
		GroupCategory groupCategory = groupCategoryService.getGroupCategoryById(groupAndCategoryDto.getGroupCategoryId());
		model.addAttribute("groupCategory", groupCategory);
		List<Category> selectedCategoryList = categorySevice.getCategoryByGroupCategoryId(groupAndCategoryDto.getGroupCategoryId());
		model.addAttribute("selectedCategoryList", selectedCategoryList);
		return "groupAndCategory";
	}

	@PostMapping("/groupAndCategory")
	String postGroupAndCategory(@ModelAttribute GroupAndCategoryDto groupAndCategoryDto, Model model) {
		GroupAndCategory groupAndCategory = new GroupAndCategory();
		Category category = new Category(groupAndCategoryDto.getCategoryId());
		GroupCategory groupCategory = new GroupCategory(groupAndCategoryDto.getGroupCategoryId());
		groupAndCategory.setCategory(category);
		groupAndCategory.setGroupCategory(groupCategory);
		try {
			groupAndCategoryService.saveGroupAndCategory(groupAndCategory);
		} catch (DataIntegrityViolationException e) {
			model.addAttribute("error", "Category is Already Exist");
			List<Category> categoryList = categorySevice.getCategory();
			List<Category> selectedCategoryList = categorySevice.getCategoryByGroupCategoryId(groupAndCategoryDto.getGroupCategoryId());
			model.addAttribute("listCategory", categoryList);
			model.addAttribute("selectedCategoryList", selectedCategoryList);
			GroupCategory groupCategory1 = groupCategoryService.getGroupCategoryById(groupAndCategoryDto.getGroupCategoryId());
			model.addAttribute("groupCategory", groupCategory1);
			return "groupAndCategory";
		}
		return "redirect:/admin/groupAndCategory?groupCategoryId="+groupAndCategoryDto.getGroupCategoryId();
	}

	@PostMapping("/groupAndCategory/delete")
	String deleteGroupAndCategory(@RequestParam(value = "categoryId") int categoryId,
			@RequestParam(value = "groupCategoryId") int groupCategoryId, Model model) {
		if (categoryId == 0 || groupCategoryId == 0) {
			return "redirect:/admin/groupAndCategory";
		}
		groupAndCategoryService.removeGroupAndCategory(categoryId, groupCategoryId);
		//List<Category> categoryList = categorySevice.getCategory();
		//List<Category> selectedCategoryList = categorySevice.getCategoryByGroupCategoryId(groupCategoryId);
		//model.addAttribute("listCategory", categoryList);
		//model.addAttribute("selectedCategoryList", selectedCategoryList);
		//GroupCategory groupCategory1 = groupCategoryService.getGroupCategoryById(groupCategoryId);
		//model.addAttribute("groupCategory", groupCategory1);
		return "redirect:/admin/groupAndCategory?groupCategoryId=" + groupCategoryId;
	}

}
