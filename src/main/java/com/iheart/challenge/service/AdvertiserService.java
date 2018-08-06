package com.iheart.challenge.service;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.iheart.challenge.BasicFunctions;
import com.iheart.challenge.ResourceNotFoundException;
import com.iheart.challenge.bean.Advertiser;
import com.iheart.challenge.converter.TypeConverters;
import com.iheart.challenge.entity.AdvertiserEntity;
import com.iheart.challenge.repo.AdvertiserRepository;

@Component
public class AdvertiserService implements BasicFunctions{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdvertiserService.class);

	@Autowired
	private AdvertiserRepository advertiserRepo;
	
	public Advertiser saveOrUpdate(final Advertiser advertiser) {
		AdvertiserEntity entity = TypeConverters.toAdvertiserEntity(advertiser);
		if(StringUtils.isEmpty(entity.getId())) {
			entity.setId(UUID.randomUUID().toString());
		}
		
		AdvertiserEntity savedEntity = advertiserRepo.save(entity);
		return TypeConverters.toAdvertiser(savedEntity);
	}
	
	public Advertiser update(final Advertiser advertiser, final String advertiserId) {
		final Advertiser existingAdvertiser = getAdvertiserById(advertiserId);

		setIfNotNull(existingAdvertiser::setName, advertiser::getName);
		setIfNotNull(existingAdvertiser::setContactName, advertiser::getContactName);
		setIfNotNull(existingAdvertiser::setCreditLimit, advertiser::getCreditLimit);
		
		return saveOrUpdate(existingAdvertiser);
		
	}
	
	public Advertiser getAdvertiserById(final String id) {
		AdvertiserEntity existingAdvertiser = null;
		try {
			existingAdvertiser = advertiserRepo.getOne(id);
			return TypeConverters.toAdvertiser(existingAdvertiser);
		}catch(EntityNotFoundException enfe) {
			LOGGER.info("Advertiser with id "+id+" not found in database.");
			throw ResourceNotFoundException.of("Advertiser");
		}
	}
	
	public void deleteAdvertiserById(final String id) {
		try {
			advertiserRepo.deleteById(id);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
