package com.greenlight.shop.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the township database table.
 * 
 */
@Entity
@NamedQuery(name="Township.findAll", query="SELECT t FROM Township t")
public class Township implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int townshipId;

	private String townshipName;
	
	private String townshipNameMm;
	
	private int deliveryCharge;

	public Township() {
	}
	

	public Township(int townshipId) {
		super();
		this.townshipId = townshipId;
	}


	public int getTownshipId() {
		return this.townshipId;
	}

	public void setTownshipId(int townshipId) {
		this.townshipId = townshipId;
	}

	public String getTownshipName() {
		return this.townshipName;
	}

	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}
	public String getTownshipNameMm() {
		return townshipNameMm;
	}
	public void setTownshipNameMm(String townshipNameMm) {
		this.townshipNameMm = townshipNameMm;
	}
	public int getDeliveryCharge() {
		return deliveryCharge;
	}
	public void setDeliveryCharge(int deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

}