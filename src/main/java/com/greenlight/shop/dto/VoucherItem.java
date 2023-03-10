package com.greenlight.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VoucherItem {
	int no;
	String item;
	int qty;
	int amount;

}
