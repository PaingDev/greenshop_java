package com.greenlight.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {
	
	@GetMapping(value = {"/","/home"})
	public String home() {
		return "index";
	}
	

}
