package com.iheart.challenge.service;

import static com.iheart.challenge.logging.LoggingSteps.*;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.iheart.challenge.ResourceNotFoundException;
import com.iheart.challenge.bean.Advertiser;
import com.iheart.challenge.converter.TypeConverters;
import com.iheart.challenge.entity.AdvertiserEntity;
import com.iheart.challenge.logging.StepLogger;
import com.iheart.challenge.repo.AdvertiserRepository;
import com.iheart.challenge.utils.Exceptions;

@Component
public class AdvertiserService implements Exceptions, StepLogger{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdvertiserService.class);

	@Autowired
	private AdvertiserRepository advertiserRepo;
	
	public Advertiser create(final Advertiser advertiser) {
		logServiceStep(CREATE_ADVERTISER);
		final Advertiser savedAdvertiser = saveOrUpdate(advertiser);
		logServiceStepComplete(CREATE_ADVERTISER);
		return savedAdvertiser;
	}
	
	public Advertiser update(final Advertiser advertiser, final String advertiserId) {
		logServiceStep(UPADTE_ADVERTISER);
		throwRuntimeExceptionOnEmpty(advertiserId, "Cannot find advertiser. Advertiser id is null/empty.", IllegalArgumentException.class);
		final Advertiser existingAdvertiser = getAdvertiserById(advertiserId);

		setIfNotNull(existingAdvertiser::setName, advertiser::getName);
		setIfNotNull(existingAdvertiser::setContactName, advertiser::getContactName);
		setIfNotNull(existingAdvertiser::setCreditLimit, advertiser::getCreditLimit);
		logServiceStepComplete(UPADTE_ADVERTISER);

		return saveOrUpdate(existingAdvertiser);
		
	}
	
	public Advertiser getAdvertiserById(final String advertiserId) {
		logServiceStep(GET_ADVERTISER);
		throwRuntimeExceptionOnEmpty(advertiserId, "Cannot find advertiser. Advertiser id is null/empty.", IllegalArgumentException.class);

		AdvertiserEntity existingAdvertiser = null;
		try {
			existingAdvertiser = advertiserRepo.getOne(advertiserId);
			if(ObjectUtils.isEmpty(existingAdvertiser)) {
				debug("Advertiser "+advertiserId+" cannot be found.");
				throw new RuntimeException("Cannot find Advetiser with id "+advertiserId+". System Error. Please check logs.");
			}
			logServiceStepComplete(GET_ADVERTISER);
			return TypeConverters.toAdvertiser(existingAdvertiser);
		}catch(EntityNotFoundException enfe) {
			debug("Advertiser with id "+advertiserId+" not found in database.");
			throw ResourceNotFoundException.of("Advertiser");
		}
	}
	
	public void deleteAdvertiserById(final String advertiserId) {
		logServiceStep(DELETE_ADVERTISER);
		throwRuntimeExceptionOnEmpty(advertiserId, "Cannot find advertiser. Advertiser id is null/empty.", IllegalArgumentException.class);

		try {
			advertiserRepo.deleteById(advertiserId);
			logServiceStepComplete(DELETE_ADVERTISER);
		}catch(EntityNotFoundException enfe) {
			debug("Advertiser with id "+advertiserId+" not found in database.");
			throw new RuntimeException("Unable to delete the advertiser with advertiser id : "+advertiserId);
		}catch(Exception e) {
			error(e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	public Logger getLogger() {
		return LOGGER;
	}
	
	private Advertiser saveOrUpdate(final Advertiser advertiser) {
		
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
}
