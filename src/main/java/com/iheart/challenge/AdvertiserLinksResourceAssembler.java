package com.iheart.challenge;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.iheart.challenge.bean.Advertiser;
import com.iheart.challenge.resource.AdvertiserResource;

public class AdvertiserLinksResourceAssembler extends ResourceAssemblerSupport<Advertiser, AdvertiserLinksResource>{
	public AdvertiserLinksResourceAssembler() {
		super(AdvertiserResource.class, AdvertiserLinksResource.class);
	}
	@Override
	public AdvertiserLinksResource toResource(Advertiser advertiser) {
//		AdvertiserLinksResource advertiserLResource = createResourceWithId(advertiser.getId(), advertiser);
//		Link advertiserLink = ControllerLinkBuilder.linkTo(methodOn(AdvertiserResource.class).getAdvertiserById(advertiser.getId())).withRel("advertiser");
//		 
//		advertiserLResource.setAdvertiser(advertiser);
//		advertiserLResource.add(advertiserLink);
//		 
//		return advertiserLResource;
		return null;
	}
}
