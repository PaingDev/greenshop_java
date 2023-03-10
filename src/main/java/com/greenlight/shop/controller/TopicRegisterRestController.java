package com.greenlight.shop.controller;

import java.util.Arrays;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.TopicManagementResponse;
import com.greenlight.shop.domain.Useraccount;
import com.greenlight.shop.service.UserServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class TopicRegisterRestController {
	
	@Autowired
	UserServiceImpl userService;
	  
	@RolesAllowed(value= {"USER"})
	@RequestMapping(value = "/{token}/rel/topics/{topic}", method = RequestMethod.POST)
	public ResponseEntity<?> subscribeTokenToTopic(@PathVariable("token") String token, @PathVariable("topic") String topic) {
		Object detail = SecurityContextHolder.getContext().getAuthentication().getDetails();
//		if(detail instanceof WebAuthenticationDetails) {
//			System.out.println("No user");
//		}else if(detail instanceof Useraccount) {
//			System.out.println("Some user login");
//			Useraccount account = (Useraccount) detail;
//			Useraccount userAccount = userService.getUserAccountById(account.getUserAccountId());
//			
//		}
		
		Useraccount account = (Useraccount) detail;
		//Useraccount userAccount = userService.getUserAccountById(account.getUserAccountId());
		
		  try {
			TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(
					  Arrays.asList(token), String.valueOf(account.getUserAccountId()));
			return ResponseEntity.ok(response);
		} catch (FirebaseMessagingException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		//return ResponseEntity.ok(response).build();
	}
}
