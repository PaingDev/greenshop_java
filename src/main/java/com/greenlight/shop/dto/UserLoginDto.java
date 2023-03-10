package com.greenlight.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {
	
	String userToken;
	String type;
	String phoneNo;
	String userName;

	public UserLoginDto(String userToken) {
		this.userToken = userToken;
	}

	public UserLoginDto(String userToken, String type) {
		super();
		this.userToken = userToken;
		this.type = type;
	}
	
	
}
