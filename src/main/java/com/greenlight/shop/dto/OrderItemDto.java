package com.greenlight.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto extends SaleItemDto{
	
	private int qty;

	@Override
	public String toString() {
		return "OrderItemDto [qty=" + qty + ", getItemId()=" + getItemId() + ", getPrice()=" + getPrice()
				+ ", getUnitId()=" + getUnitId() + "]";
	}


}
