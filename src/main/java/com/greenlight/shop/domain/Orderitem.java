package com.greenlight.shop.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenlight.shop.util.IDGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the orderitem database table.
 * 
 */
@Entity
@NamedQuery(name="Orderitem.findAll", query="SELECT o FROM Orderitem o")
public class Orderitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderItemId;

	private int amount;

	private int deliveryFee;

	private int discount;

	@Temporal(TemporalType.DATE)
	private Date orderDate;

	private int total;
	
	private String address;
	
	private String phoneNo;
	
	private String preferedTime;
	
	// 'ORDER','ACCEPT','REJECT','COMPLETE'
	private String status;
	
	@Temporal(TemporalType.DATE)
	private Date acceptDate;
	@Temporal(TemporalType.DATE)
	private Date rejectDate;
	@Temporal(TemporalType.DATE)
	private Date completeDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="townshipId")
	private Township township;

	//bi-directional many-to-one association to Useraccount
	@ManyToOne
	@JoinColumn(name="userAccountId")
	private Useraccount useraccount;

	@JsonIgnore
	//bi-directional many-to-one association to Orderitemlist
	@OneToMany(mappedBy="orderitem")
	private List<Orderitemlist> orderitemlists;

	public Orderitem() {
	}

	public int getOrderItemId() {
		return this.orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getDeliveryFee() {
		return this.deliveryFee;
	}

	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public int getDiscount() {
		return this.discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Useraccount getUseraccount() {
		return this.useraccount;
	}

	public void setUseraccount(Useraccount useraccount) {
		this.useraccount = useraccount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPreferedTime() {
		return preferedTime;
	}

	public void setPreferedTime(String preferedTime) {
		this.preferedTime = preferedTime;
	}

	public Township getTownship() {
		return township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Orderitemlist> getOrderitemlists() {
		return this.orderitemlists;
	}

	public void setOrderitemlists(List<Orderitemlist> orderitemlists) {
		this.orderitemlists = orderitemlists;
	}
	
	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	public Date getRejectDate() {
		return rejectDate;
	}

	public void setRejectDate(Date rejectDate) {
		this.rejectDate = rejectDate;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Transient
	public String getEncryptedId() {
		return IDGenerator.generateId(getOrderItemId());
	}

	public Orderitemlist addOrderitemlist(Orderitemlist orderitemlist) {
		List<Orderitemlist> list = getOrderitemlists();
		if(list == null) {
			list = new ArrayList<Orderitemlist>();
		}
		list.add(orderitemlist);
		orderitemlist.setOrderitem(this);

		return orderitemlist;
	}

	public Orderitemlist removeOrderitemlist(Orderitemlist orderitemlist) {
		getOrderitemlists().remove(orderitemlist);
		orderitemlist.setOrderitem(null);

		return orderitemlist;
	}

}