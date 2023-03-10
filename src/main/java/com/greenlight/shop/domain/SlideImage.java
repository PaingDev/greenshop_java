package com.greenlight.shop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@NamedQuery(name="SlideImage.findAll", query="SELECT s FROM SlideImage s")
public class SlideImage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int slideImageId;
	
	String img;
	
	@Transient
	String action;
	
	@Transient
	MultipartFile imagePart;
	
	public SlideImage() {
		super();
	}
	public SlideImage(int slideImageId, String img) {
		super();
		this.slideImageId = slideImageId;
		this.img = img;
	}
	public int getSlideImageId() {
		return slideImageId;
	}
	public void setSlideImageId(int slideImageId) {
		this.slideImageId = slideImageId;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
	public MultipartFile getImagePart() {
		return imagePart;
	}
	public void setImagePart(MultipartFile imagePart) {
		this.imagePart = imagePart;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	
	
}
