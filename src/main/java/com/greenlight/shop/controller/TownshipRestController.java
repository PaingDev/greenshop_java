package com.greenlight.shop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenlight.shop.dto.TownshipDto;
import com.greenlight.shop.service.TownshipService;

@RestController
@RequestMapping("/api/v1")
public class TownshipRestController {
	
	@Autowired
	TownshipService townshipService;
	
	@GetMapping("/township")
	public List<TownshipDto> listTownship(){
		List<TownshipDto> listTownship = new ArrayList<>();
		listTownship = townshipService.getTownship().stream().map(m->{
			return new TownshipDto(m.getTownshipId(), m.getTownshipName(), m.getTownshipNameMm(), m.getDeliveryCharge());
		}).collect(Collectors.toList());
		return listTownship;
	}

}
