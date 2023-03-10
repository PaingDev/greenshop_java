package com.greenlight.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseToken;
import com.greenlight.shop.JwtTokenProvider;
import com.greenlight.shop.domain.Useraccount;
import com.greenlight.shop.dto.FBProfile;
import com.greenlight.shop.dto.UserLoginDto;
import com.greenlight.shop.repository.UserAccountRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserAccountRepo userAccountRepo;
	
	@Autowired
	JwtTokenProvider tokenProvider;

	@Override
	public String loginOrRegister(FBProfile fbProfile) {
		Useraccount userAccount = userAccountRepo.findByFacebookId(fbProfile.getId());
		if(userAccount == null) {
			userAccount = new Useraccount();
			userAccount.setEmail(fbProfile.getEmail());
			userAccount.setUserRole("USER");
			userAccount.setFacebookId(fbProfile.getId());
			userAccount.setUserName(fbProfile.getName());
			userAccount.setStatus("ACTIVE");
			userAccountRepo.save(userAccount);
		}else {
			userAccount.setUserName(fbProfile.getName());
			if(!"".equals(fbProfile.getEmail())) {
				userAccount.setEmail(fbProfile.getEmail());
			}
			
		}
		return String.valueOf(userAccount.getUserAccountId());
	}

	public Useraccount getUserAccountById(int userId) {
		Useraccount userAccount = userAccountRepo.findById(userId).orElseGet(null);
		return userAccount;
	}

	@Override
	public UserLoginDto loginOrRegisterFirebase(FirebaseToken decodedToken) {
		System.out.println(decodedToken.getClaims());
		String phoneNumber = (String) decodedToken.getClaims().get("phone_number");
		//{aud=freshpoint-99d6c, auth_time=1591379603, exp=1591383203, iat=1591379603, iss=https://securetoken.google.com/freshpoint-99d6c, sub=4YtufzmWgIcb09iEfEyIu9g8ln32, user_id=4YtufzmWgIcb09iEfEyIu9g8ln32, phone_number=+95970074560, firebase={identities={phone=[+95970074560]}, sign_in_provider=phone}}
		String uid = decodedToken.getUid();
		
		Useraccount userAccount = userAccountRepo.findByFirebaseId(uid);
		if(userAccount == null) {
			userAccount = new Useraccount();
			userAccount.setFirebaseId(uid);
			userAccount.setUserName(phoneNumber);
			userAccount.setPhoneNo(phoneNumber);
			userAccount.setUserRole("USER");
			userAccount.setStatus("ACTIVE");
			userAccountRepo.save(userAccount);
		}else {
			
		}
		String userId = String.valueOf(userAccount.getUserAccountId());
		String jwtToken = tokenProvider.generateToken(userId, 
				userAccount.getUsername(), null, userAccount.getFirebaseId());
		
		return new UserLoginDto(jwtToken, "phone",phoneNumber, phoneNumber);
	}

	public List<Useraccount> getUser() {
		List<Useraccount> listUser = userAccountRepo.findAll();
		return listUser;
	}

}
