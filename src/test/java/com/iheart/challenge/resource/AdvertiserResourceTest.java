
package com.iheart.challenge.resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.iheart.challenge.ResourceNotFoundException;
import com.iheart.challenge.bean.Advertiser;
import com.iheart.challenge.io.TestDataUtils;
import com.iheart.challenge.service.AdvertiserService;


@RunWith(MockitoJUnitRunner.class)
public class AdvertiserResourceTest {
	
	@Mock
	private AdvertiserService service;
	
	@InjectMocks
	private AdvertiserResource resource = new AdvertiserResource();
	
	private Advertiser advertiser = null;
	
	@Before
	public void initEachTest() {
		advertiser = TestDataUtils.read("advertiser.json", Advertiser.class);
	}
	
	@Test
	public void testCreate() {
		Mockito.when(service.create(Mockito.any(Advertiser.class))).thenReturn(advertiser);
		final ResponseEntity<?> response = resource.create(advertiser);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	public void testCreate400() {
		advertiser.setName("Venk@ta");
		final ResponseEntity<?> response = resource.create(advertiser);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void testCreate500() {
		Mockito.when(service.create(Mockito.any(Advertiser.class))).thenThrow(RuntimeException.class);
		final ResponseEntity<?> response = resource.create(advertiser);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testUpdate() {
		Mockito.when(service.update(Mockito.any(Advertiser.class), Mockito.anyString())).thenReturn(advertiser);
		final ResponseEntity<?> response = resource.update(advertiser, advertiser.getId());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testUpdate400() {
		advertiser.setName("Venk@ta");
		final ResponseEntity<?> response = resource.update(advertiser, advertiser.getId());
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void testUpdate404() {
		Mockito.when(service.update(Mockito.any(Advertiser.class), Mockito.anyString())).thenThrow(ResourceNotFoundException.class);
		final ResponseEntity<?> response = resource.update(advertiser, advertiser.getId());
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void testUpdate500() {
		Mockito.when(service.update(Mockito.any(Advertiser.class), Mockito.anyString())).thenThrow(RuntimeException.class);
		final ResponseEntity<?> response = resource.update(advertiser, advertiser.getId());
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testDelete() {
		Mockito.doNothing().when(service).deleteAdvertiserById(Mockito.anyString());
		final ResponseEntity<?> response = resource.delete(advertiser.getId());
		Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void testDelete500() {
		Mockito.doThrow(RuntimeException.class).when(service).deleteAdvertiserById(Mockito.anyString());
		final ResponseEntity<?> response = resource.delete(advertiser.getId());
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testGet() {
		Mockito.when(service.getAdvertiserById(Mockito.anyString())).thenReturn(advertiser);
		final ResponseEntity<?> response = resource.getByAdvertiserId(advertiser.getId());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testGet404() {
		Mockito.when(service.getAdvertiserById(Mockito.anyString())).thenThrow(ResourceNotFoundException.class);
		final ResponseEntity<?> response = resource.getByAdvertiserId(advertiser.getId());
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void testGet500() {
		Mockito.when(service.getAdvertiserById(Mockito.anyString())).thenThrow(RuntimeException.class);
		final ResponseEntity<?> response = resource.getByAdvertiserId(advertiser.getId());
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
}
