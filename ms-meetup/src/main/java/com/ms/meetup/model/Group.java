package com.ms.meetup.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="MEETUP_GROUP")
public class Group implements Serializable{
	private static final long serialVersionUID = 8503056830846613415L;

	@Id
	@GeneratedValue
	@Column(name="GROUP_ID")
	private Long id;
	
	@Column(name="GROUP_NAME")
	//@JsonProperty("name")
	private String name;
	
	@Column(name="GROUP_DESC")
	//@JsonProperty("description")
	private String description;
	
	@Column(name="CREATED_BY")
	//@JsonProperty("createdBy")
	private String createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
