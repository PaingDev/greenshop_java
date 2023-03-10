package com.greenlight.shop.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the orderitemlist database table.
 * 
 */
@Entity
@NamedQuery(name="Orderitemlist.findAll", query="SELECT o FROM Orderitemlist o")
public class Orderitemlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderItemListId;

	private int amount;

	private String itemName;
	
	private int qty;

	private int unitPrice;
	
	//bi-directional many-to-one association to Orderitem
	@ManyToOne
	@JoinColumn(name="orderItemId")
	private Orderitem orderitem;

	//bi-directional many-to-one association to Sellingprice
	@ManyToOne
	@JoinColumn(name="priceId")
	private Sellingprice sellingprice;

	public Orderitemlist() {
	}
	
	

	public Orderitemlist(int amount, String itemName, int qty, int unitPrice, Sellingprice sellingprice) {
		super();
		this.amount = amount;
		this.itemName = itemName;
		this.qty = qty;
		this.unitPrice = unitPrice;
		this.sellingprice = sellingprice;
	}



	public int getOrderItemListId() {
		return this.orderItemListId;
	}

	public void setOrderItemListId(int orderItemListId) {
		this.orderItemListId = orderItemListId;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQty() {
		return this.qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Orderitem getOrderitem() {
		return this.orderitem;
	}

	public void setOrderitem(Orderitem orderitem) {
		this.orderitem = orderitem;
	}

	public Sellingprice getSellingprice() {
		return this.sellingprice;
	}

	public void setSellingprice(Sellingprice sellingprice) {
		this.sellingprice = sellingprice;
	}

}