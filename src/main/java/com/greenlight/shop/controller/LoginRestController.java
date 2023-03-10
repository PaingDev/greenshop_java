package com.greenlight.shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.firebase.auth.FirebaseToken;
import com.greenlight.shop.JwtTokenProvider;
import com.greenlight.shop.dto.FBProfile;
import com.greenlight.shop.dto.UserLoginDto;
import com.greenlight.shop.service.FirebaseService;
import com.greenlight.shop.service.UserService;

@RequestMapping("/api/v1")
@RestController
public class LoginRestController {

	@Autowired
	UserService userService;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	FirebaseService firebaseService;
	

	String baseUrl = "https://graph.facebook.com/v6.0/me";
	
	//eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJ1c2VySWRcIjpcIjJcIixcInVzZXJOYW1lXCI6XCIxNzM3MTUzNzQ5NzY3Mzc4XCIsXCJmYklkXCI6XCJQYWluZyBQeWFlIFNvbmVcIn0iLCJpYXQiOjE1ODUyMjM5OTEsImlzcyI6IjE3MzcxNTM3NDk3NjczNzgiLCJleHAiOjE1ODU4Mjg3OTF9.n2eX4vPWfF09nwpD4WeO5chC56ivJF4q01SKNtHof-KMgDGVXCfITPtbpGIBo5La-fCgfwPf_oIwRrYvbQq5Cw
	@PostMapping("/userLogin")
	public UserLoginDto userLogin(@RequestParam("token") String token) {
		System.out.println(token);
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("token", token);
		FBProfile fbProfile = restTemplate.getForObject(baseUrl + "?fields=id,name,email&access_token={token}",
				FBProfile.class, vars);
		if (fbProfile != null) {
			String userId = userService.loginOrRegister(fbProfile);
			String jwtToken = tokenProvider.generateToken(userId, 
			fbProfile.getName(),fbProfile.getId(), null);
			
			return new UserLoginDto(jwtToken, "facebook","", fbProfile.getName());
		}
		return null;
	}
	
	@PostMapping("/userLogin/phone")
	public UserLoginDto userLoginWithPhone(@RequestParam("token") String idToken) {
		FirebaseToken decodedToken = firebaseService.getFirebaseUId(idToken);
		
		UserLoginDto userLoginDto = userService.loginOrRegisterFirebase(decodedToken);
		return userLoginDto;
	}

}
