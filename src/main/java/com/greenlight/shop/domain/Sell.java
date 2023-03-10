package com.greenlight.shop.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


@Entity
@Immutable
public class Sell implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int priceId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private int unitPrice;

	@JsonIgnore
	//bi-directional many-to-one association to Orderitemlist
	@OneToMany(mappedBy="sellingprice")
	private List<Orderitemlist> orderitemlists;

	//bi-directional many-to-one association to Item
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="itemId")
	private Item item;

	//bi-directional many-to-one association to Unit
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="unitId")
	private Unit unit;

	public Sell() {
	}

	public int getPriceId() {
		return this.priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public List<Orderitemlist> getOrderitemlists() {
		return this.orderitemlists;
	}

	public void setOrderitemlists(List<Orderitemlist> orderitemlists) {
		this.orderitemlists = orderitemlists;
	}

	public Orderitemlist removeOrderitemlist(Orderitemlist orderitemlist) {
		getOrderitemlists().remove(orderitemlist);
		orderitemlist.setSellingprice(null);

		return orderitemlist;
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