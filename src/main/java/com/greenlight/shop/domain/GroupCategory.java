package com.greenlight.shop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GroupCategory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int groupCategoryId;
	
	private String groupCategoryName;
	
	private String groupCategoryNameMm;
	
	private String frontImg;
	
	public GroupCategory() {
		super();
	}
	
	

	public GroupCategory(int groupCategoryId) {
		super();
		this.groupCategoryId = groupCategoryId;
	}



	public int getGroupCategoryId() {
		return groupCategoryId;
	}

	public void setGroupCategoryId(int groupCategoryId) {
		this.groupCategoryId = groupCategoryId;
	}

	public String getGroupCategoryName() {
		return groupCategoryName;
	}

	public void setGroupCategoryName(String groupCategoryName) {
		this.groupCategoryName = groupCategoryName;
	}

	public String getGroupCategoryNameMm() {
		return groupCategoryNameMm;
	}

	public void setGroupCategoryNameMm(String groupCategoryNameMm) {
		this.groupCategoryNameMm = groupCategoryNameMm;
	}

	public String getFrontImg() {
		return frontImg;
	}

	public void setFrontImg(String frontImg) {
		this.frontImg = frontImg;
	}
	
	
	
}
