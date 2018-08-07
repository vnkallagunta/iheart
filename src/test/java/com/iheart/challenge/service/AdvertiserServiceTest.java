package com.iheart.challenge.service;

import javax.persistence.EntityNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.iheart.challenge.ResourceNotFoundException;
import com.iheart.challenge.bean.Advertiser;
import com.iheart.challenge.entity.AdvertiserEntity;
import com.iheart.challenge.io.TestDataUtils;
import com.iheart.challenge.repo.AdvertiserRepository;

@RunWith(MockitoJUnitRunner.class)
public class AdvertiserServiceTest {

	@Mock
	private AdvertiserRepository advertiserRepo;
	
	@InjectMocks
	private AdvertiserService service = new AdvertiserService();
	
	@Before
	public void initEachTest() {
	}
	
	@Test
	public void testCreate() {
		final Advertiser advertiser = TestDataUtils.read("advertiser.json", Advertiser.class);
		final AdvertiserEntity advertiserEntity = TestDataUtils.read("advertiser-entity.json", AdvertiserEntity.class);
		Mockito.when(advertiserRepo.save(Mockito.any(AdvertiserEntity.class))).thenReturn(advertiserEntity);
		
		advertiser.setId(null);
		final Advertiser savedAdvertiser = service.saveOrUpdate(advertiser);
		
		Assert.assertNotNull(savedAdvertiser);
		Assert.assertNotNull(savedAdvertiser.getId());
		Assert.assertEquals(advertiser.getName(), savedAdvertiser.getName());
		Assert.assertEquals(advertiser.getContactName(), savedAdvertiser.getContactName());
		Assert.assertEquals(advertiser.getCreditLimit(), savedAdvertiser.getCreditLimit(), 0);
	}
	
	@Test(expected = RuntimeException.class)
	public void testCreateFail() {
		final Advertiser advertiser = TestDataUtils.read("advertiser.json", Advertiser.class);
		Mockito.when(advertiserRepo.save(Mockito.any(AdvertiserEntity.class))).thenReturn(null);
		
		advertiser.setId(null);
		service.saveOrUpdate(advertiser);
	}
	
	@Test
	public void testUpdate() {
		final Advertiser advertiser = TestDataUtils.read("advertiser.json", Advertiser.class);
		final AdvertiserEntity advertiserEntity = TestDataUtils.read("advertiser-entity.json", AdvertiserEntity.class);
		Mockito.when(advertiserRepo.save(Mockito.any(AdvertiserEntity.class))).thenReturn(advertiserEntity);
		Mockito.when(advertiserRepo.getOne(Mockito.anyString())).thenReturn(advertiserEntity);
		
		final Advertiser savedAdvertiser = service.update(advertiser, advertiser.getId());
		
		Assert.assertNotNull(savedAdvertiser);
		Assert.assertNotNull(savedAdvertiser.getId());
		Assert.assertEquals(advertiser.getName(), savedAdvertiser.getName());
		Assert.assertEquals(advertiser.getContactName(), savedAdvertiser.getContactName());
		Assert.assertEquals(advertiser.getCreditLimit(), savedAdvertiser.getCreditLimit(), 0);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateNonExistingAdvertiser() {
		final Advertiser advertiser = TestDataUtils.read("advertiser.json", Advertiser.class);
		Mockito.doThrow(EntityNotFoundException.class).when(advertiserRepo).getOne(Mockito.anyString());
		service.update(advertiser, advertiser.getId());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateWithEmptyOrNullAdvertiserId() {
		final Advertiser advertiser = TestDataUtils.read("advertiser.json", Advertiser.class);
		service.update(advertiser, null);
	}
	
	@Test(expected = RuntimeException.class)
	public void testUpdateSystemError() {
		final Advertiser advertiser = TestDataUtils.read("advertiser.json", Advertiser.class);
		Mockito.when(advertiserRepo.getOne(Mockito.anyString())).thenReturn(null);
		service.update(advertiser, advertiser.getId());
	}
	
	@Test
	public void testGetById() {
		final AdvertiserEntity advertiserEntity = TestDataUtils.read("advertiser-entity.json", AdvertiserEntity.class);
		Mockito.when(advertiserRepo.getOne(Mockito.anyString())).thenReturn(advertiserEntity);
		final Advertiser advertiser = service.getAdvertiserById(advertiserEntity.getId());
		
		Assert.assertNotNull(advertiser);
		Assert.assertEquals(advertiserEntity.getId(), advertiser.getId());
		Assert.assertEquals(advertiserEntity.getName(), advertiser.getName());
		Assert.assertEquals(advertiserEntity.getContactName(), advertiser.getContactName());
		Assert.assertEquals(advertiserEntity.getCreditLimit(), advertiser.getCreditLimit(), 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetByIdWithEmptyOrNullAdvertiserId() {
		service.getAdvertiserById(null);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testGetByIdNonExistingAdvertiser() {
		Mockito.doThrow(EntityNotFoundException.class).when(advertiserRepo).getOne(Mockito.anyString());
		service.getAdvertiserById("1234");
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetByIdSystemError() {
		final Advertiser advertiser = TestDataUtils.read("advertiser.json", Advertiser.class);
		Mockito.when(advertiserRepo.getOne(Mockito.anyString())).thenReturn(null);
		service.getAdvertiserById(advertiser.getId());
	}
	
	
	@Test
	public void testDeleteById() {
		Mockito.doNothing().when(advertiserRepo).deleteById(Mockito.anyString());
		service.deleteAdvertiserById("1234");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteByIdWithEmptyOrNullAdvertiserId() {
		service.deleteAdvertiserById(null);
	}
	
	@Test(expected = RuntimeException.class)
	public void testDeleteByIdNonExistingAdvertiser() {
		Mockito.doThrow(EntityNotFoundException.class).when(advertiserRepo).deleteById(Mockito.anyString());
		service.deleteAdvertiserById("1234");
	}
	
	@Test(expected = RuntimeException.class)
	public void testDeleteByIdSystemError() {
		final Advertiser advertiser = TestDataUtils.read("advertiser.json", Advertiser.class);
		Mockito.doThrow(Exception.class).when(advertiserRepo).deleteById(Mockito.anyString());
		service.deleteAdvertiserById(advertiser.getId());
	}
}
