package com.greenlight.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.greenlight.shop.domain.Category;
import com.greenlight.shop.domain.Item;
import com.greenlight.shop.dto.CategoryDto;
import com.greenlight.shop.dto.SaleItemDto;
import com.greenlight.shop.dto.SellingItemDto;
import com.greenlight.shop.service.CategoryService;
import com.greenlight.shop.service.ItemService;
import com.greenlight.shop.util.CurrentPath;

@Controller
@RequestMapping("/admin")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ItemService itemService;

	@ModelAttribute("selectedMenu")
	public String selectedMenu() {
		return "category";
	}

	@GetMapping("/category")
	String showCategroy(@RequestParam(value = "catId", required = false, defaultValue = "0") int catId, Model model) {
		List<Category> listCategory = categoryService.getCategory();

		model.addAttribute("listCategory", listCategory);
		List<SellingItemDto> item = new ArrayList<SellingItemDto>();
		if (catId == 0) {

			item = itemService.getItemList().stream().map(m -> {
				SellingItemDto sellingItem = new SellingItemDto(m);
				if (m.getSellingprices().isEmpty()) {
					sellingItem.setSelling(false);
				} else {
					sellingItem.setSelling(true);
					sellingItem.setUnitId(m.getSellingprices().get(0).getUnit().getUnitId());
				}
				return sellingItem;
			}).collect(Collectors.toList());
			// item.addAll();
			System.out.println("Item Size" + item.size());
			model.addAttribute("categoryName", "All Category");
		} else {
			Category category = listCategory.stream().filter(p -> p.getCategoryId() == catId).findAny().get();

			if (category != null) {

				item = itemService.getItemListByCategoryId(category.getCategoryId()).stream().map(m -> {
					SellingItemDto sellingItem = new SellingItemDto(m);
					if (m.getSellingprices().isEmpty()) {
						sellingItem.setSelling(false);
					} else {
						sellingItem.setSelling(true);
						sellingItem.setUnitId(m.getSellingprices().get(0).getUnit().getUnitId());
					}
					return sellingItem;
				}).collect(Collectors.toList());
				System.out.println("Item Size" + item.size());

				model.addAttribute("categoryName", category.getCategoryName() + "/" + category.getCategoryNameMm());
			}
		}
		
		model.addAttribute("listItem", item);

		return "categoryTable";
	}

	@GetMapping("/category/create")
	String getCategory(@ModelAttribute("categoryDto") CategoryDto categoryDto) {
		categoryDto.setCategoryId(0);
		categoryDto.setCategoryName("");
		categoryDto.setCategoryNameMm("");
		return "createCategory";
	}

	@PostMapping("/category/create")
	String postCreateCategory(@ModelAttribute("categoryDto") CategoryDto categoryDto) {
		categoryDto.setCategoryId(0);
		Category cat = new Category();
		cat.setCategoryId(categoryDto.getCategoryId());
		cat.setCategoryName(categoryDto.getCategoryName());
		cat.setCategoryNameMm(categoryDto.getCategoryNameMm());
		cat.setSpecialId(categoryDto.getSpecialId());

		try {
			MultipartFile iFile = categoryDto.getImagePart();
			if (iFile != null && !iFile.isEmpty()) {
				String name = iFile.getOriginalFilename();
				String ext = name.substring(name.lastIndexOf("."));
				String[] filePath = CurrentPath.generateNameAndPath("category", ext);

				cat.setImg(filePath[0]);

				iFile.transferTo(new File(filePath[1]));

			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		categoryService.saveCategory(cat);

		return "redirect:/admin/category/create";
	}

	@GetMapping("/category/{categoryId}/edit")
	String editCategory(@ModelAttribute("categoryDto") @Valid CategoryDto categoryDto,
			@PathVariable("categoryId") int categoryId) {
		if (categoryDto.getCategoryId() == 0) {
			return "redirect:/admin/category";
		}
		CategoryDto dto = categoryService.getCategoryById(categoryId);
		categoryDto.setCategoryName(dto.getCategoryName());
		categoryDto.setCategoryNameMm(dto.getCategoryNameMm());
		categoryDto.setImg(dto.getImg());
		categoryDto.setSpecialId(dto.getSpecialId());
		return "createCategory";
	}

	@PostMapping("/category/{categoryId}/edit")
	String postEditCategory(@ModelAttribute("categoryDto") CategoryDto categoryDto,
			@PathVariable("categoryId") int categoryId, BindingResult bind) {
		System.out.println("CategoryId");
		CategoryDto catDto = categoryService.getCategoryById(categoryId);
		categoryDto.setImg(catDto.getImg());
		Category cat = new Category();
		cat.setCategoryId(categoryId);
		cat.setCategoryName(categoryDto.getCategoryName());
		cat.setCategoryNameMm(categoryDto.getCategoryNameMm());
		cat.setSpecialId(categoryDto.getSpecialId());
		cat.setImg(categoryDto.getImg());

		try {
			MultipartFile iFile = categoryDto.getImagePart();
			if (iFile != null && !iFile.isEmpty()) {
				String name = iFile.getOriginalFilename();
				String ext = name.substring(name.lastIndexOf("."));
				String[] filePath = CurrentPath.generateNameAndPath("category", ext);

				cat.setImg(filePath[0]);

				iFile.transferTo(new File(filePath[1]));

			}

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		categoryService.updateCategory(cat);
		return "redirect:/admin/category";
	}

	@PostMapping("/category/{categoryId}/delete")
	String deleteCategory(@PathVariable("categoryId") int categoryId) {
		if (categoryId == 0) {
			return "redirect:/admin/category";
		}
		categoryService.removeCategory(categoryId);
		return "redirect:/admin/category";
	}
	
	@GetMapping("/category/{categoryId}/item")
	String showItemByCategroy(@PathVariable("categoryId") int catId, @RequestParam(defaultValue = "", required = false,value = "q") String text, @RequestParam(defaultValue = "1", required = false, value = "p") int page , Model model) {
		
		//List<SellingItemDto> item = new ArrayList<SellingItemDto>();
		
		if (catId == 0) {

//			Page<SellingItemDto> item = itemService.getItemList(text, catId, page)().stream().map(m -> {
//				SellingItemDto sellingItem = new SellingItemDto(m);
//				if (m.getSellingprices().isEmpty()) {
//					sellingItem.setSelling(false);
//				} else {
//					sellingItem.setSelling(true);
//					sellingItem.setUnitId(m.getSellingprices().get(0).getUnit().getUnitId());
//				}
//				return sellingItem;
//			}).collect(Collectors.toList());
//			// item.addAll();
//			model.addAttribute("listItem", item);
			model.addAttribute("categoryName", "All Category");
		} else {
			CategoryDto category = categoryService.getCategoryById(catId);
			model.addAttribute("categoryName", category.getCategoryName() + "/" + category.getCategoryNameMm());
			model.addAttribute("categoryId", category.getCategoryId());
		}
		Page<SellingItemDto> item = itemService.getItemList(text, catId, page);
		System.out.println(item.getContent());
		model.addAttribute("listItem", item);
		

		return "itemTable";
	}
	
	@GetMapping("/category/{categoryId}/item/unlisted")
	String showUnlistedItemByCategroy(@PathVariable("categoryId") int catId, @RequestParam(defaultValue = "", required = false,value = "q") String text, @RequestParam(defaultValue = "1", required = false, value = "p") int page , Model model) {
		
		//List<SellingItemDto> item = new ArrayList<SellingItemDto>();
		
		if (catId == 0) {

//			Page<SellingItemDto> item = itemService.getItemList(text, catId, page)().stream().map(m -> {
//				SellingItemDto sellingItem = new SellingItemDto(m);
//				if (m.getSellingprices().isEmpty()) {
//					sellingItem.setSelling(false);
//				} else {
//					sellingItem.setSelling(true);
//					sellingItem.setUnitId(m.getSellingprices().get(0).getUnit().getUnitId());
//				}
//				return sellingItem;
//			}).collect(Collectors.toList());
//			// item.addAll();
//			model.addAttribute("listItem", item);
			model.addAttribute("categoryName", "All Category");
		} else {
			CategoryDto category = categoryService.getCategoryById(catId);
			model.addAttribute("categoryName", category.getCategoryName() + "/" + category.getCategoryNameMm());
			model.addAttribute("categoryId", category.getCategoryId());
		}
		Page<SellingItemDto> item = itemService.getUnlistedItemList(text, catId, page);
		System.out.println(item.getContent());
		
		model.addAttribute("listItem", item);
		

		return "unlistItemTable";
	}

}
