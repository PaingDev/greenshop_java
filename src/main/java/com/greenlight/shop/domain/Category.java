package com.greenlight.shop.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int categoryId;

	private String categoryName;
	
	private String categoryNameMm;
	
	private int specialId;
	
	private String img;

	@JsonIgnore
	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="category")
	private List<Item> items;

	public Category() {
	}
	

	public Category(int categoryId) {
		super();
		this.categoryId = categoryId;
	}



	public Category(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}



	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryNameMm() {
		return categoryNameMm;
	}


	public void setCategoryNameMm(String categoryNameMm) {
		this.categoryNameMm = categoryNameMm;
	}

	public int getSpecialId() {
		return specialId;
	}

	public void setSpecialId(int specialId) {
		this.specialId = specialId;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setCategory(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setCategory(null);

		return item;
	}

	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}
	
	

}