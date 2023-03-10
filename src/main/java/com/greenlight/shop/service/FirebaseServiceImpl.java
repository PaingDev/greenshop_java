package com.greenlight.shop.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@Service
public class FirebaseServiceImpl implements FirebaseService {
	
	@PostConstruct
	void initialize() {
		FirebaseOptions options;
		try {
			String userDirectory = FileSystems.getDefault()
			        .getPath("")
			        .toAbsolutePath()
			        .toString();
			String configFile = userDirectory+"/firebaseconfig/freshpoint-99d6c-firebase-adminsdk-ivas1-3c0f45892b.json";
			options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(new FileInputStream(configFile)))
					.setDatabaseUrl("https://freshpoint-99d6c.firebaseio.com").build();
			FirebaseApp.initializeApp(options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public FirebaseToken getFirebaseUId(String idToken) {
		try {
			FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
			return decodedToken;
		} catch (FirebaseAuthException e) {
			e.printStackTrace();
		}
		return null;
	}
}
