package com.ms.meetup.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORIES")
public class Categories implements Serializable{
	private static final long serialVersionUID = -2033507808491370874L;

	@Id
	@GeneratedValue
	@Column(name="ID")
	private String id;
	
	@Column(name="CATEGORY_NAME")
	private String categoryName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
