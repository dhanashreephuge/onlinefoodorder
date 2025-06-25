package com.flavourheights.dto;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
public class RestaurantDto {
	private String title;
	
	@Column(length = 1000)
	private List<String> images;
	
	private String description;
	private Long id;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	@Override
//	public boolean equals(Object o) {
//	    if (this == o) return true;
//	    if (o == null || getClass() != o.getClass()) return false;
//
//	    RestaurantDto that = (RestaurantDto) o;
//
//	    return id != null ? id.equals(that.id) : that.id == null;
//	}
//
//	@Override
//	public int hashCode() {
//	    return id != null ? id.hashCode() : 0;
//	}
//
//	
	
	
}
