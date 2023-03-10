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

import com.greenlight.shop.domain.Category;
import com.greenlight.shop.domain.Item;
import com.greenlight.shop.dto.BasicOrderItem;
import com.greenlight.shop.dto.CategoryDto;
import com.greenlight.shop.service.CategoryService;
import com.greenlight.shop.service.ItemService;
import com.greenlight.shop.util.CurrentPath;

@RequestMapping("/admin")
@Controller
public class ItemController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ItemService itemService;

	@ModelAttribute("selectedMenu")
	public String selectedMenu() {
		return "item";
	}

	@GetMapping("/item/create")
	String getItemCreate(@ModelAttribute BasicOrderItem basicOrderItem, Model model) {
		List<CategoryDto> listCategory = categoryService.getAllCategory();
		model.addAttribute("listCategory", listCategory);
		return "createItem";
	}

	@PostMapping("/item/create")
	String postItemCreate(@ModelAttribute @Validated BasicOrderItem basicOrderItem, BindingResult bind, Model model) {
		List<CategoryDto> listCategory = categoryService.getAllCategory();
		model.addAttribute("listCategory", listCategory);
		if (bind.hasErrors()) {
			return "createItem";
		}

		Item item = new Item();
		MultipartFile fFile = basicOrderItem.getFrontImg();
		MultipartFile bFile = basicOrderItem.getBackImg();
		try {
			// front Image
			if (fFile != null && !fFile.isEmpty()) {
				String name = fFile.getOriginalFilename();
				String ext = name.substring(name.lastIndexOf("."));
				String[] filePath = CurrentPath.generateNameAndPath("front", ext);
				item.setFrontImg(filePath[0]);
				fFile.transferTo(new File(filePath[1]));
			}

			// back Image
			if (bFile != null && !bFile.isEmpty()) {
				String bName = bFile.getOriginalFilename();
				String bExt = bName.substring(bName.lastIndexOf("."));
				String[] bFilePath = CurrentPath.generateNameAndPath("back", bExt);
				item.setBackImg(bFilePath[0]);
				bFile.transferTo(new File(bFilePath[1]));
			}

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		item.setItemName(basicOrderItem.getItemName());
		item.setItemNameMm(basicOrderItem.getItemNameMm());
		item.setDiscountPrice(basicOrderItem.getDiscountPrice());
		item.setCategory(new Category(basicOrderItem.getCategoryId()));
		item.setStatus("ACTIVE");
		itemService.saveItem(item);
		return "redirect:/admin/item/"+item.getItemId()+"/edit";
	}

	@GetMapping("/item/{itemId}/edit")
	String getItemEdit(@ModelAttribute BasicOrderItem basicOrderItem, Model model, @PathVariable("itemId") int itemId) {
		List<CategoryDto> listCategory = categoryService.getAllCategory();
		model.addAttribute("listCategory", listCategory);
		Item item = itemService.getItemById(itemId);
		if (item != null) {
			model.addAttribute("item", item);
		}
		basicOrderItem.setItemId(item.getItemId());
		basicOrderItem.setItemName(item.getItemName());
		basicOrderItem.setItemNameMm(item.getItemNameMm());
		basicOrderItem.setDiscountPrice(item.getDiscountPrice());
		
		basicOrderItem.setCategoryId(item.getCategory().getCategoryId());
		
		return "createItem";
	}

	@PostMapping("/item/{itemId}/edit")
	String postItemEdit(@ModelAttribute @Validated BasicOrderItem basicOrderItem, Model model, BindingResult bind,
			@PathVariable("itemId") int itemId) {
		System.out.println("PostMethod Working");
		List<CategoryDto> listCategory = categoryService.getAllCategory();
		model.addAttribute("listCategory", listCategory);
		Item item = itemService.getItemById(itemId);
		

		if (item != null) {
			model.addAttribute("item", item);
		}
		if (bind.hasErrors()) {
			return "createItem";
		}
		MultipartFile fFile = basicOrderItem.getFrontImg();
		MultipartFile bFile = basicOrderItem.getBackImg();
		try {
			// front Image
			if (fFile != null && !fFile.isEmpty()) {
				String name = fFile.getOriginalFilename();
				String ext = name.substring(name.lastIndexOf("."));
				String[] filePath = CurrentPath.generateNameAndPath("front", ext);
				item.setFrontImg(filePath[0]);
				fFile.transferTo(new File(filePath[1]));

			}

			// back Image
			if (bFile != null && !bFile.isEmpty()) {
				String bName = bFile.getOriginalFilename();
				String bExt = bName.substring(bName.lastIndexOf("."));
				String[] bFilePath = CurrentPath.generateNameAndPath("back", bExt);
				item.setBackImg(bFilePath[0]);
				bFile.transferTo(new File(bFilePath[1]));
			}

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		item.setItemName(basicOrderItem.getItemName());
		item.setItemNameMm(basicOrderItem.getItemNameMm());
		item.setDiscountPrice(basicOrderItem.getDiscountPrice());
		item.setCategory(new Category(basicOrderItem.getCategoryId()));
		itemService.saveItem(item);

		return "redirect:/admin/item/" + itemId + "/edit";
	}

	@PostMapping("/item/{itemId}/image/delete")
	String deleteItemImage(@PathVariable("itemId") int itemId, @RequestParam(value = "img") String img) {
		Item item = itemService.getItemById(itemId);
		if (item != null) {

			if ("front".equals(img)) {
				File file = new File(CurrentPath.userDirectory + "/items/" + item.getFrontImg());
				file.delete();
				item.setFrontImg(null);
				itemService.saveItem(item);
			} else if ("back".equals(img)) {
				File file = new File(CurrentPath.userDirectory + "/items/" + item.getBackImg());
				file.delete();
				item.setBackImg(null);
				itemService.saveItem(item);
			}
		}
		return "redirect:/admin/item/" + itemId + "/edit";
	}

	@PostMapping("/item/{itemId}/delete")
	String deleteItem(@PathVariable("itemId") int itemId) {
		if (itemId == 0) {
			return "redirect:/admin/category";
		}
		itemService.removeItem(itemId);
		return "redirect:/admin/category";
	}
	
	@GetMapping("/item/{itemId}")
	String hideShowAction(@PathVariable("itemId") int itemId, @RequestParam("hide") boolean hide) {
		itemService.hideItem(itemId, hide);
		return "redirect:/admin/item/" + itemId + "/edit";
	}
}
