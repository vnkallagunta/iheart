package com.iheart.challenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Advertiser")
public class AdvertiserEntity {
	@Id
	@Column(name="ADVERTISER_ID")
	private String id;
	
	@Column(name = "ADVERTISER_NAME")
	private String name;
	
	@Column(name = "ADVERTISER_CONTACT_NAME")
	private String contactName;
	
	@Column(name = "ADVERTISER_CREDIT_LIMIT")
	private double creditLimit;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}


}
