package com.greenlight.shop.dto;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TownshipDto {
	
	@Min(value = 1, message = "Id must not zero")
	private int townshipId;
	private String townshipName;
	private String townshipNameMm;
	private int deliveryCharge;
	

}
