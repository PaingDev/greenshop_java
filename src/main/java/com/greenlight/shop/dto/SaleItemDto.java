package com.greenlight.shop.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleItemDto {
	
	int itemId;
	String itemName;
	String itemNameMm;
	int discountPrice;
	int price;
	String currency;
	String unitName;
	String unitNameMm;
	int unitId;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	Date date;
	
	String frontImg;
	String backImg;
	
	public SaleItemDto(int itemId, String itemName, int price, String currency, String unitName) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
		this.currency = currency;
		this.unitName = unitName;
	}

	public SaleItemDto(int itemId, String itemName, int price, String currency, String unitName, int unitId) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
		this.currency = currency;
		this.unitName = unitName;
		this.unitId = unitId;
	}

	public SaleItemDto(int itemId, String itemName, int price, String currency, String unitName, int unitId,
			Date date) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
		this.currency = currency;
		this.unitName = unitName;
		this.unitId = unitId;
		this.date = date;
	}

	public SaleItemDto(int itemId, String itemName, int price, String currency, String unitName, int unitId, Date date,
			String frontImg, String backImg) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
		this.currency = currency;
		this.unitName = unitName;
		this.unitId = unitId;
		this.date = date;
		this.frontImg = frontImg;
		this.backImg = backImg;
	}

	public SaleItemDto(int itemId, String itemName, String itemNameMm, int price, String currency, String unitName,
			String unitNameMm, int unitId, Date date, String frontImg, String backImg) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemNameMm = itemNameMm;
		this.price = price;
		this.currency = currency;
		this.unitName = unitName;
		this.unitNameMm = unitNameMm;
		this.unitId = unitId;
		this.date = date;
		this.frontImg = frontImg;
		this.backImg = backImg;
	}
	
	public SaleItemDto(int itemId, String itemName, String itemNameMm,int discountPrice, int price, String currency, String unitName,
			String unitNameMm, int unitId, Date date, String frontImg, String backImg) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemNameMm = itemNameMm;
		this.discountPrice=discountPrice;
		this.price = price;
		this.currency = currency;
		this.unitName = unitName;
		this.unitNameMm = unitNameMm;
		this.unitId = unitId;
		this.date = date;
		this.frontImg = frontImg;
		this.backImg = backImg;
	}

	public SaleItemDto(int itemId, String itemName, String itemNameMm, String frontImg) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemNameMm = itemNameMm;
		this.frontImg = frontImg;
	}
	
	
	

}
