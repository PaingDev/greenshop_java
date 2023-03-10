package com.greenlight.shop.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int itemId;

	private String backImg;

	private String frontImg;

	private String itemName;
	
	private String itemNameMm;
	
	private String status;
	
	private int discountPrice;

	public int getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	//bi-directional many-to-one association to Category
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="categoryId")
	private Category category;

	@JsonIgnore
	//bi-directional many-to-one association to Purchaseitem
	@OneToMany(mappedBy="item")
	private List<Purchaseitem> purchaseitems;

	@JsonIgnore
	//bi-directional many-to-one association to Sellingprice
	@OneToMany(mappedBy="item")
	private List<Sellingprice> sellingprices;

	public Item() {
	}

	public Item(int itemId) {
		this.itemId = itemId;
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getBackImg() {
		return this.backImg;
	}

	public void setBackImg(String backImg) {
		this.backImg = backImg;
	}

	public String getFrontImg() {
		return this.frontImg;
	}

	public void setFrontImg(String frontImg) {
		this.frontImg = frontImg;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String getItemNameMm() {
		return itemNameMm;
	}

	public void setItemNameMm(String itemNameMm) {
		this.itemNameMm = itemNameMm;
	}

	public List<Purchaseitem> getPurchaseitems() {
		return this.purchaseitems;
	}

	public void setPurchaseitems(List<Purchaseitem> purchaseitems) {
		this.purchaseitems = purchaseitems;
	}

	public Purchaseitem addPurchaseitem(Purchaseitem purchaseitem) {
		getPurchaseitems().add(purchaseitem);
		purchaseitem.setItem(this);

		return purchaseitem;
	}

	public Purchaseitem removePurchaseitem(Purchaseitem purchaseitem) {
		getPurchaseitems().remove(purchaseitem);
		purchaseitem.setItem(null);

		return purchaseitem;
	}

	public List<Sellingprice> getSellingprices() {
		return this.sellingprices;
	}

	public void setSellingprices(List<Sellingprice> sellingprices) {
		this.sellingprices = sellingprices;
	}

	public Sellingprice addSellingprice(Sellingprice sellingprice) {
		getSellingprices().add(sellingprice);
		sellingprice.setItem(this);

		return sellingprice;
	}

	public Sellingprice removeSellingprice(Sellingprice sellingprice) {
		getSellingprices().remove(sellingprice);
		sellingprice.setItem(null);

		return sellingprice;
	}

}