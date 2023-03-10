package com.greenlight.shop.service;

import com.google.firebase.auth.FirebaseToken;
import com.greenlight.shop.dto.FBProfile;
import com.greenlight.shop.dto.UserLoginDto;

public interface UserService {

	String loginOrRegister(FBProfile fbProfile);

	UserLoginDto loginOrRegisterFirebase(FirebaseToken decodedToken);

}
