package com.greenlight.shop.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the purchaseitem database table.
 * 
 */
@Entity
@NamedQuery(name="Purchaseitem.findAll", query="SELECT p FROM Purchaseitem p")
public class Purchaseitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int purchaseItemId;

	private int price;

	private int qty;

	private int unitPrice;

	//bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name="itemId")
	private Item item;

	//bi-directional many-to-one association to Unit
	@ManyToOne
	@JoinColumn(name="unitId")
	private Unit unit;

	public Purchaseitem() {
	}

	public int getPurchaseItemId() {
		return this.purchaseItemId;
	}

	public void setPurchaseItemId(int purchaseItemId) {
		this.purchaseItemId = purchaseItemId;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}