package com.greenlight.shop.service;

import com.google.firebase.auth.FirebaseToken;

public interface FirebaseService {

	FirebaseToken getFirebaseUId(String idToken);
}
