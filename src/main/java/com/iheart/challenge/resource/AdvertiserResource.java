package com.iheart.challenge.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iheart.challenge.bean.Advertiser;
import com.iheart.challenge.service.AdvertiserService;

@RestController
@RequestMapping("advertisers")
public class AdvertiserResource {
	
	@Autowired
	AdvertiserService service;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Advertiser> create(@RequestBody Advertiser advertiser) {
		validate(advertiser);

		final Advertiser savedAdvertiser = service.create(advertiser);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedAdvertiser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	private void validate(final Advertiser advertiser) {
		
	}
	
	public static void main(String args[]) throws Exception{
		final Advertiser advertiser = new Advertiser().setId("1234").setName("Venkata").setContactName("Venkata").setCreditLimit(100.00);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(System.out, advertiser);
	}
}
