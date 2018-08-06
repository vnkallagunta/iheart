
package com.iheart.challenge.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iheart.challenge.ResourceNotFoundException;
import com.iheart.challenge.bean.Advertiser;
import com.iheart.challenge.response.InternalServerError;
import com.iheart.challenge.response.NotFound;
import com.iheart.challenge.service.AdvertiserService;
import com.iheart.challenge.validate.AdvertiserValidator;
import com.iheart.challenge.validate.ValidationError;

@SuppressWarnings({"unchecked", "rawtypes"})
@RestController
@RequestMapping("advertisers")
public class AdvertiserResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdvertiserService.class);
	
	@Autowired
	AdvertiserService service;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody Advertiser advertiser) {
		ResponseEntity<?> errors = validateCreate(advertiser);
		if(errors != null) {
			return errors;
		}
		advertiser.setId(null);
		final Advertiser savedAdvertiser = service.saveOrUpdate(advertiser);
        return new ResponseEntity<Advertiser>(savedAdvertiser, HttpStatus.CREATED);
	}
	
	
	@PutMapping("{advertiserId}")
	public ResponseEntity<?> update(final @RequestBody Advertiser advertiser, final @PathVariable String advertiserId) {
		ResponseEntity<?> errors = validateUpdate(advertiser);
		if(errors != null) {
			return errors;
		}
		try {
			final Advertiser updatedAdvertiser = service.update(advertiser, advertiserId);
			return new ResponseEntity<Advertiser>(updatedAdvertiser, HttpStatus.OK);
		}catch(ResourceNotFoundException rnfe) {
			return new ResponseEntity(new NotFound("Advertiser with id "+advertiserId+" not found."), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity(new InternalServerError("Unexpected System Error. Please try later."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("{advertiserId}")
	public ResponseEntity<?> delete(final @PathVariable String advertiserId) {
		try {
			service.deleteAdvertiserById(advertiserId);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity(new InternalServerError("Unexpected System Error. Please try later."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("{advertiserId}")
	public ResponseEntity<?> getByAdvertiserId(final @PathVariable String advertiserId) {
		try {
			final Advertiser existingAdvertiser = service.getAdvertiserById(advertiserId);
			return new ResponseEntity<Advertiser>(existingAdvertiser, HttpStatus.OK);
		}catch(ResourceNotFoundException rnfe) {
			return new ResponseEntity(new NotFound("Advertiser with id "+advertiserId+" not found."), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity(new InternalServerError("Unexpected System Error. Please try later."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private ResponseEntity<?> validateCreate(final Advertiser advertiser) {
		List<ValidationError> errors = AdvertiserValidator.of(advertiser)
				.validateCreateAdvertiser()
				.errors();
		if(!errors.isEmpty()) {
			return new ResponseEntity<List<ValidationError>>(errors, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
	
	private ResponseEntity<?> validateUpdate(final Advertiser advertiser) {
		List<ValidationError> errors = AdvertiserValidator.of(advertiser)
				.validateUpdateAdvertiser()
				.errors();
		if(!errors.isEmpty()) {
			return new ResponseEntity<List<ValidationError>>(errors, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
	
	public static void main(String args[]) throws Exception{
		final Advertiser advertiser = new Advertiser().setId("1234").setName("Venkata").setContactName("Venkata").setCreditLimit(100.00);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(System.out, advertiser);
	}
}

