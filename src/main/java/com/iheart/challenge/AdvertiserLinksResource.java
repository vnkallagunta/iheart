package com.iheart.challenge;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.iheart.challenge.bean.Advertiser;

public class AdvertiserLinksResource  extends ResourceSupport {

	@JsonUnwrapped
	private Advertiser advertiser;
	 
	public Advertiser getAdvertiser() {
	return advertiser;
	}
	 
	public void setAdvertiser(Advertiser advertiser) {
	this.advertiser = advertiser;
	}
}
