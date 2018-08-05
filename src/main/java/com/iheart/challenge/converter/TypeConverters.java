package com.iheart.challenge.converter;

import com.iheart.challenge.bean.Advertiser;
import com.iheart.challenge.entity.AdvertiserEntity;

public class TypeConverters{
	public static final AdvertiserEntity toAdvertiserEntity(final Advertiser bean) {
		final AdvertiserEntity entity = new AdvertiserEntity();
		entity.setId(bean.getId());
		entity.setName(bean.getName());
		entity.setContactName(bean.getContactName());
		entity.setCreditLimit(bean.getCreditLimit());
		
		return entity;
	}
	
	public static final Advertiser toAdvertiser(final AdvertiserEntity entity) {
		final Advertiser bean = new Advertiser();
		return bean.setId(entity.getId())
				.setName(entity.getName())
				.setContactName(entity.getContactName())
				.setCreditLimit(bean.getCreditLimit())
		;
	}
}
