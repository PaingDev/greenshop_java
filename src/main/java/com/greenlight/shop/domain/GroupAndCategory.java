package com.greenlight.shop.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GroupAndCategory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int groupAndCategoryId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="groupCategoryId")
	private GroupCategory groupCategory;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="categoryId")
	private Category category;

	public int getGroupAndCategoryId() {
		return groupAndCategoryId;
	}

	public void setGroupAndCategoryId(int groupAndCategoryId) {
		this.groupAndCategoryId = groupAndCategoryId;
	}

	public GroupCategory getGroupCategory() {
		return groupCategory;
	}

	public void setGroupCategory(GroupCategory groupCategory) {
		this.groupCategory = groupCategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
