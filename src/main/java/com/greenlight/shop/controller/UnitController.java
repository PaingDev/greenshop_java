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

import com.greenlight.shop.domain.Unit;
import com.greenlight.shop.dto.UnitDto;
import com.greenlight.shop.service.UnitService;

@Controller
@RequestMapping("/admin")
public class UnitController {
	
	@Autowired
	UnitService unitService;
	
	@ModelAttribute("selectedMenu")
	public String selectedMenu() {
		return "unit";
	}
	
	@GetMapping("/unit")
	String getUnit(Model model) {
		List<Unit> listUnit = unitService.getUnitList();
		model.addAttribute("listUnit", listUnit);
		return "unit";
	}
	
	
	@GetMapping("/unit/create")
	String getCreateUnit(@ModelAttribute("unitDto") UnitDto unitDto) {
		unitDto.setUnitId(0);
		unitDto.setUnitName("");
		unitDto.setUnitNameMm("");
		return "createUnit";
	}
	
	@PostMapping("/unit/create")
	String postCreateUnit(@ModelAttribute("unitDto") UnitDto unitDto) {
		unitDto.setUnitId(0);
		Unit unit = new Unit();
		System.out.println(unitDto);
		unit.setUnitId(unitDto.getUnitId());
		unit.setUnitName(unitDto.getUnitName());
		unit.setUnitNameMm(unitDto.getUnitNameMm());
		unitService.saveUnit(unit);
		return "redirect:/admin/unit";
	}
	
	@GetMapping("/unit/{unitId}/edit")
	String editCategory(@ModelAttribute("unitDto") UnitDto unitDto, @PathVariable("unitId") int unitId) {
		if(unitDto.getUnitId() == 0) {
			return "redirect:/admin/unit";
		}
		Unit unit = unitService.getUnitById(unitId);
		unitDto.setUnitName(unit.getUnitName());
		unitDto.setUnitNameMm(unit.getUnitNameMm());
		return "createUnit";
	}
	
	@PostMapping("/unit/{unitId}/edit")
	String postEditCategory(@ModelAttribute("unitDto") @Valid UnitDto unitDto,  @PathVariable("unitId") int unitId) {
		Unit unit = new Unit();
		unit.setUnitId(unitDto.getUnitId());
		unit.setUnitName(unitDto.getUnitName());
		unit.setUnitNameMm(unitDto.getUnitNameMm());
		unitService.updateUnit(unit);
		return "redirect:/admin/unit";
	}
	
	@PostMapping("/unit/{unitId}/delete")
	String deleteUnit(@PathVariable("unitId") int unitId) {
		if(unitId == 0) {
			return "redirect:/admin/unit";
		}
		unitService.removeUnit(unitId);
		return "redirect:/admin/unit";
	}
	
	
	
	
}
