package com.greenlight.shop.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the unit database table.
 * 
 */
@Entity
@NamedQuery(name="Unit.findAll", query="SELECT u FROM Unit u")
public class Unit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int unitId;

	private String unitName;
	
	private String unitNameMm;

	@JsonIgnore
	//bi-directional many-to-one association to Purchaseitem
	@OneToMany(mappedBy="unit")
	private List<Purchaseitem> purchaseitems;

	@JsonIgnore
	//bi-directional many-to-one association to Sellingprice
	@OneToMany(mappedBy="unit")
	private List<Sellingprice> sellingprices;

	public Unit() {
	}

	public Unit(int unitId) {
		this.unitId = unitId;
	}

	public int getUnitId() {
		return this.unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	public String getUnitNameMm() {
		return unitNameMm;
	}

	public void setUnitNameMm(String unitNameMm) {
		this.unitNameMm = unitNameMm;
	}

	public List<Purchaseitem> getPurchaseitems() {
		return this.purchaseitems;
	}

	public void setPurchaseitems(List<Purchaseitem> purchaseitems) {
		this.purchaseitems = purchaseitems;
	}

	public Purchaseitem addPurchaseitem(Purchaseitem purchaseitem) {
		getPurchaseitems().add(purchaseitem);
		purchaseitem.setUnit(this);

		return purchaseitem;
	}

	public Purchaseitem removePurchaseitem(Purchaseitem purchaseitem) {
		getPurchaseitems().remove(purchaseitem);
		purchaseitem.setUnit(null);

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
		sellingprice.setUnit(this);

		return sellingprice;
	}

	public Sellingprice removeSellingprice(Sellingprice sellingprice) {
		getSellingprices().remove(sellingprice);
		sellingprice.setUnit(null);

		return sellingprice;
	}

}