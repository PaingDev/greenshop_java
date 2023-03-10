package com.greenlight.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenlight.shop.dto.LoginDto;
import com.greenlight.shop.service.CustomUserDetailService;

@Controller
@RequestMapping("/admin")
public class LoginController {

	@Autowired
	CustomUserDetailService userDetailService;

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	PasswordEncoder passEncoder;

	
	@GetMapping("/login")
	public String login(@ModelAttribute("login") LoginDto loginDto) {
		return "adminLogin";
	}

	@PostMapping("/login")
	public String postLogin(@ModelAttribute("login") @Validated LoginDto loginDto, BindingResult bind, Model model) {
		System.out.println("/login");
		if (bind.hasErrors()) {
			return "adminLogin";
		}
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.getUserName(),
					loginDto.getPassword());
			authManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(token);
			return "redirect:/admin/home";
		} catch (BadCredentialsException e) {
			return "adminLogin";
		}
	}
	

}
