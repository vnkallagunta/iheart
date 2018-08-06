package com.iheart.challenge.bean;

import java.io.Serializable;

public class Advertiser implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String name;
	
	private String contactName;
	
	private double creditLimit;

	public String getId() {
		return id;
	}

	public Advertiser setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Advertiser setName(String name) {
		this.name = name;
		return this;
	}

	public String getContactName() {
		return contactName;
	}

	public Advertiser setContactName(String contactName) {
		this.contactName = contactName;
		return this;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public Advertiser setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
		return this;
	}
}