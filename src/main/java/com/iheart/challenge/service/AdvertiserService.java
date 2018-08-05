package com.iheart.challenge.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iheart.challenge.bean.Advertiser;
import com.iheart.challenge.converter.TypeConverters;
import com.iheart.challenge.entity.AdvertiserEntity;
import com.iheart.challenge.repo.AdvertiserRepository;

@Component
public class AdvertiserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdvertiserService.class);

	@Autowired
	private AdvertiserRepository advertiserRepo;
	
	public Advertiser create(final Advertiser advertiser) {
		AdvertiserEntity entity = TypeConverters.toAdvertiserEntity(advertiser);
		entity = advertiserRepo.save(entity);
		return TypeConverters.toAdvertiser(entity);
		
	}
}
