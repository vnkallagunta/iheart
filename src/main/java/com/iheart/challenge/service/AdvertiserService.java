package com.iheart.challenge.service;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
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
		
		if(ObjectUtils.isEmpty(savedEntity)) {
			throw new RuntimeException("Cannot save Advetiser. System Error. Please check logs.");
		}
		return TypeConverters.toAdvertiser(savedEntity);
	}
	
	public Advertiser update(final Advertiser advertiser, final String advertiserId) {
		if(StringUtils.isEmpty(advertiserId)) {
			throw new IllegalArgumentException("Cannot find advertiser. Advertiser id is null/empty.");
		}
		final Advertiser existingAdvertiser = getAdvertiserById(advertiserId);

		setIfNotNull(existingAdvertiser::setName, advertiser::getName);
		setIfNotNull(existingAdvertiser::setContactName, advertiser::getContactName);
		setIfNotNull(existingAdvertiser::setCreditLimit, advertiser::getCreditLimit);
		
		return saveOrUpdate(existingAdvertiser);
		
	}
	
	public Advertiser getAdvertiserById(final String advertiserId) {
		if(StringUtils.isEmpty(advertiserId)) {
			throw new IllegalArgumentException("Cannot find advertiser. Advertiser id is null/empty.");
		}
		AdvertiserEntity existingAdvertiser = null;
		try {
			existingAdvertiser = advertiserRepo.getOne(advertiserId);
			if(ObjectUtils.isEmpty(existingAdvertiser)) {
				throw new RuntimeException("Cannot find Advetiser with id "+advertiserId+". System Error. Please check logs.");
			}
			return TypeConverters.toAdvertiser(existingAdvertiser);
		}catch(EntityNotFoundException enfe) {
			LOGGER.info("Advertiser with id "+advertiserId+" not found in database.");
			throw ResourceNotFoundException.of("Advertiser");
		}
	}
	
	public void deleteAdvertiserById(final String advertiserId) {
		if(StringUtils.isEmpty(advertiserId)) {
			throw new IllegalArgumentException("Cannot find advertiser. Advertiser id is null/empty.");
		}
		try {
			advertiserRepo.deleteById(advertiserId);
		}catch(EntityNotFoundException enfe) {
			LOGGER.info("Advertiser with id "+advertiserId+" not found in database.");
			throw new RuntimeException("Unable to delete the advertiser with advertiser id : "+advertiserId);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
