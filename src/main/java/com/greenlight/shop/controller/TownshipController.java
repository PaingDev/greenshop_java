package com.greenlight.shop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenlight.shop.domain.Township;
import com.greenlight.shop.dto.TownshipDto;
import com.greenlight.shop.service.TownshipService;

@Controller
@RequestMapping("/admin")
public class TownshipController {
	@Autowired
	TownshipService townshipService;
	
	@ModelAttribute("selectedMenu")
	public String selectedMenu() {
		return "township";
	}
	
	@GetMapping("/township")
	public String township(Model model) {
		List<Township> listTownship = townshipService.getTownship();
		model.addAttribute("listTownship", listTownship);
		return "township";
	}
	

	@GetMapping("/township/create")
	String getCreateUnit(@ModelAttribute("townshipDto") TownshipDto townshipDto) {
		System.out.println("Create");
		townshipDto.setTownshipId(0);
		townshipDto.setTownshipName("");
		townshipDto.setTownshipNameMm("");
		townshipDto.setDeliveryCharge(0);
		return "createTownship";
	}
	
	@PostMapping("/township/create")
	String postCreateUnit(@ModelAttribute("townshipDto") TownshipDto townshipDto) {
		townshipDto.setTownshipId(0);
		Township township = new Township();
		township.setTownshipId(townshipDto.getTownshipId());
		township.setTownshipName(townshipDto.getTownshipName());
		township.setTownshipNameMm(townshipDto.getTownshipNameMm());
		township.setDeliveryCharge(townshipDto.getDeliveryCharge());
		townshipService.saveTownship(township);
		return "redirect:/admin/township";
	}
	
	@GetMapping("/township/{townshipId}/edit")
	String editCategory(@ModelAttribute("townshipDto") TownshipDto townshipDto, @PathVariable("townshipId") int townshipId) {
		if(townshipDto.getTownshipId() == 0) {
			return "redirect:/admin/unit";
		}
		Township township = townshipService.getTownshipById(townshipId);
		townshipDto.setTownshipName(township.getTownshipName());
		townshipDto.setTownshipNameMm(township.getTownshipNameMm());
		townshipDto.setDeliveryCharge(township.getDeliveryCharge());
		return "createTownship";
	}
	
	@PostMapping("/township/{townshipId}/edit")
	String postEditCategory(@ModelAttribute("townshipDto") @Valid TownshipDto townshipDto,  @PathVariable("townshipId") int townshipId) {
		Township township = new Township();
		township.setTownshipId(townshipDto.getTownshipId());
		township.setTownshipName(townshipDto.getTownshipName());
		township.setTownshipNameMm(townshipDto.getTownshipNameMm());
		township.setDeliveryCharge(townshipDto.getDeliveryCharge());
		townshipService.updateTownship(township);
		return "redirect:/admin/township";
	}
	
	@PostMapping("/township/{townshipId}/delete")
	String deleteUnit(@PathVariable("townshipId") int townshipId) {
		if(townshipId == 0) {
			return "redirect:/admin/township";
		}
		townshipService.removeTownship(townshipId);
		return "redirect:/admin/township";
	}
	

}
