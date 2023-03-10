package com.greenlight.shop.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
	
	@NotEmpty(message = "User Name is empty!")
	private String userName;
	@NotEmpty(message = "Password is empty!")
	private String password;
	
}
