package com.greenlight.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenlight.shop.domain.Useraccount;
import com.greenlight.shop.repository.LoginRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	LoginRepo loginRepo;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Useraccount userAccount = loginRepo.getUseraccountByUserName(username);
		if(userAccount == null) {
			throw new UsernameNotFoundException("Username not found.");
		}
		return userAccount;
	}
	
	public Useraccount getUserByUserName(String userName) {
		Useraccount userAccount = loginRepo.getUseraccountByUserName(userName);
		return userAccount;
	}

}
