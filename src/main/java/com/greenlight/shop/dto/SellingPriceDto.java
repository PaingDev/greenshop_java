package com.greenlight.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellingPriceDto {
	
	int priceId;
	int unitId;
	int itemId;
	int price;

}
