package com.greenlight.shop.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenData {
	String userId;// can be firebase uid
	String userName;
	String fbId;
	String type;
	String firebaseId;
	
//	@JsonIgnore
//	int getDecryptedUserId(){
//		return Integer.parseInt(Cryption.decryption(userId));
//	}

}
