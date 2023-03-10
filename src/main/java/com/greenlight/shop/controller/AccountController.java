package com.greenlight.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenlight.shop.domain.Useraccount;
import com.greenlight.shop.service.UserServiceImpl;

@Controller()
@RequestMapping("/admin")
public class AccountController {
	
	@Autowired
	UserServiceImpl userService;
	
	@ModelAttribute("selectedMenu")
	public String selectedMenu() {
		return "account";
	}
	
	@GetMapping("/account")
	public String getAccount(Model model) {
		List<Useraccount> listAccount = userService.getUser();
		model.addAttribute("listAccount", listAccount);
		return "account";
	}
}
