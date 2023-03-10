package com.greenlight.shop.dto;

import com.greenlight.shop.domain.Item;
import com.greenlight.shop.domain.Sell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SellingItemDto{
	private boolean selling;
	
	private Item item;
	private int unitId;
	private Sell sellingPrice;

	public boolean isSelling() {
		return selling;
	}

	public void setSelling(boolean selling) {
		this.selling = selling;
	}

	public SellingItemDto(Item item) {
		super();
		this.item = item;
	}
	
	
}
