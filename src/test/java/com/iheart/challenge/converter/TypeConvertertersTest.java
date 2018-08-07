package com.iheart.challenge.converter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.iheart.challenge.bean.Advertiser;
import com.iheart.challenge.entity.AdvertiserEntity;
import com.iheart.challenge.io.TestDataUtils;


@RunWith(MockitoJUnitRunner.class)
public class TypeConvertertersTest {
	
	@Test
	public void testToAdvertiserEntity() {
		final Advertiser advertiser = TestDataUtils.read("advertiser.json", Advertiser.class);
		final AdvertiserEntity advertiserEntity = TypeConverters.toAdvertiserEntity(advertiser);
		
		Assert.assertNotNull(advertiser);
		Assert.assertEquals(advertiser.getId(), advertiserEntity.getId());
		Assert.assertEquals(advertiser.getName(), advertiserEntity.getName());
		Assert.assertEquals(advertiser.getContactName(), advertiserEntity.getContactName());
		Assert.assertEquals(advertiser.getCreditLimit(), advertiser.getCreditLimit(), 0);
	}
	
	@Test
	public void testToAdvertiser() {
		final AdvertiserEntity advertiserEntity = TestDataUtils.read("advertiser-entity.json", AdvertiserEntity.class);
		final Advertiser advertiser = TypeConverters.toAdvertiser(advertiserEntity);
		
		Assert.assertNotNull(advertiser);
		Assert.assertEquals(advertiserEntity.getId(), advertiser.getId());
		Assert.assertEquals(advertiserEntity.getName(), advertiser.getName());
		Assert.assertEquals(advertiserEntity.getContactName(), advertiser.getContactName());
		Assert.assertEquals(advertiserEntity.getCreditLimit(), advertiser.getCreditLimit(), 0);

	}
}
