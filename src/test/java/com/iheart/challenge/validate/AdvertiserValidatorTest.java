package com.iheart.challenge.validate;

import static com.iheart.challenge.validate.AdvertiserValidator.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.iheart.challenge.bean.Advertiser;
import com.iheart.challenge.io.TestDataUtils;


@RunWith(MockitoJUnitRunner.class)
public class AdvertiserValidatorTest {
	
	private Advertiser advertiser;
	private AdvertiserValidator validator;
	private List<ValidationError> errors;
	
	@Before
	public void initEachTest() {
		advertiser = TestDataUtils.read("advertiser.json", Advertiser.class);
		validator = AdvertiserValidator.of(advertiser);
	}
	
	@Test
	public void testValidateCreateAdvertiserWithNameEmpty() {
		advertiser.setName("");
		validator.validateCreateAdvertiser();
		errors = validator.errors();
		
		Assert.assertFalse(errors.isEmpty());
		azzert(errors.get(0), Empty.class, NAME_ATTRIBUTE);
	}
	
	@Test
	public void testValidateCreateAdvertiserWithNameExceededLength() {
		advertiser.setName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuv");
		validator.validateCreateAdvertiser();
		errors = validator.errors();
		
		Assert.assertFalse(errors.isEmpty());
		azzert(errors.get(0), TooBig.class, NAME_ATTRIBUTE);
	}
	
	@Test
	public void testValidateCreateAdvertiserWithNameContainingSpecialCharacters() {
		advertiser.setName("Venk@ta");
		validator.validateCreateAdvertiser();
		errors = validator.errors();
		
		Assert.assertFalse(errors.isEmpty());
		azzert(errors.get(0), InvalidValue.class, NAME_ATTRIBUTE);
	}
	
	@Test
	public void testValidateCreateAdvertiserWithContactNameEmpty() {
		advertiser.setContactName("");
		validator.validateCreateAdvertiser();
		errors = validator.errors();
		
		Assert.assertFalse(errors.isEmpty());
		azzert(errors.get(0), Empty.class, CONTACT_NAME_ATTRIBUTE);
	}
	
	@Test
	public void testValidateCreateAdvertiserWithContactNameExceededLength() {
		advertiser.setContactName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuv");
		validator.validateCreateAdvertiser();
		errors = validator.errors();Assert.assertFalse(errors.isEmpty());
		azzert(errors.get(0), TooBig.class, CONTACT_NAME_ATTRIBUTE);
	}
	
	@Test
	public void testValidateCreateAdvertiserWithContactNameContainingSpecialCharacters() {
		advertiser.setContactName("Venk@ta");
		validator.validateCreateAdvertiser();
		errors = validator.errors();Assert.assertFalse(errors.isEmpty());
		azzert(errors.get(0), InvalidValue.class, CONTACT_NAME_ATTRIBUTE);
	}
	
	@Test
	public void testValidateCreateWithCreditLimitNegative() {
		advertiser.setCreditLimit(-1.0);
		validator.validateCreateAdvertiser();
		errors = validator.errors();
		
		Assert.assertFalse(errors.isEmpty());
		azzert(errors.get(0), InvalidValue.class, CREDIT_LIMIT_ATTRIBUTE);
	}
	
	@Test
	public void testValidateCreateWithCreditLimitExcessive() {
		advertiser.setCreditLimit(100000.00);
		validator.validateCreateAdvertiser();
		errors = validator.errors();
		
		Assert.assertFalse(errors.isEmpty());
		azzert(errors.get(0), TooBig.class, CREDIT_LIMIT_ATTRIBUTE);
	}
	
	@Test
	public void testValidateCreateWithMultipleErrors() {
		advertiser.setName(null);
		advertiser.setContactName("Venk@ta");
		advertiser.setCreditLimit(-1.00);
		validator.validateCreateAdvertiser();
		errors = validator.errors();
		
		Assert.assertFalse(errors.isEmpty());
		azzert(errors.get(0), Empty.class, NAME_ATTRIBUTE);
		azzert(errors.get(1), InvalidValue.class, CONTACT_NAME_ATTRIBUTE);
		azzert(errors.get(2), InvalidValue.class, CREDIT_LIMIT_ATTRIBUTE);
	}
	
	@Test
	public void testValidateCreateWithNoErrors() {
		validator.validateCreateAdvertiser();
		errors = validator.errors();
		
		Assert.assertTrue(errors.isEmpty());
	}
	
	@Test
	public void testValidateUpdateAdvertiserWithNameExceededLength() {
		advertiser.setName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuv");
		validator.validateUpdateAdvertiser();
		errors = validator.errors();
		
		Assert.assertFalse(errors.isEmpty());
		azzert(errors.get(0), TooBig.class, NAME_ATTRIBUTE);
	}
	
	@Test
	public void testValidateUpdateAdvertiserWithNameContainingSpecialCharacters() {
		advertiser.setName("Venk@ta");
		validator.validateUpdateAdvertiser();
		errors = validator.errors();
		
		Assert.assertFalse(errors.isEmpty());
		azzert(errors.get(0), InvalidValue.class, NAME_ATTRIBUTE);
	}
	
	
	@Test
	public void testValidateUpdateAdvertiserWithContactNameExceededLength() {
		advertiser.setContactName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuv");
		validator.validateUpdateAdvertiser();
		errors = validator.errors();Assert.assertFalse(errors.isEmpty());
		azzert(errors.get(0), TooBig.class, CONTACT_NAME_ATTRIBUTE);
	}
	
	@Test
	public void testValidateUpdateAdvertiserWithContactNameContainingSpecialCharacters() {
		advertiser.setContactName("Venk@ta");
		validator.validateUpdateAdvertiser();
		errors = validator.errors();Assert.assertFalse(errors.isEmpty());
		azzert(errors.get(0), InvalidValue.class, CONTACT_NAME_ATTRIBUTE);
	}
	
	@Test
	public void testValidateUpdateWithCreditLimitNegative() {
		advertiser.setCreditLimit(-1.0);
		validator.validateUpdateAdvertiser();
		errors = validator.errors();
		
		Assert.assertFalse(errors.isEmpty());
		azzert(errors.get(0), InvalidValue.class, CREDIT_LIMIT_ATTRIBUTE);
	}
	
	@Test
	public void testValidateUpdateWithCreditLimitExcessive() {
		advertiser.setCreditLimit(100000.00);
		validator.validateUpdateAdvertiser();
		errors = validator.errors();
		
		Assert.assertFalse(errors.isEmpty());
		azzert(errors.get(0), TooBig.class, CREDIT_LIMIT_ATTRIBUTE);
	}
	
	@Test
	public void testValidateUpdateWithMultipleErrors() {
		advertiser.setName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuv");
		advertiser.setContactName("Venk@ta");
		advertiser.setCreditLimit(-1.00);
		validator.validateUpdateAdvertiser();
		errors = validator.errors();
		
		Assert.assertFalse(errors.isEmpty());
		azzert(errors.get(0), Empty.class, NAME_ATTRIBUTE);
		azzert(errors.get(1), InvalidValue.class, CONTACT_NAME_ATTRIBUTE);
		azzert(errors.get(2), InvalidValue.class, CREDIT_LIMIT_ATTRIBUTE);
	}
	
	@Test
	public void testValidateUpdateWithNoErrors() {
		validator.validateUpdateAdvertiser();
		errors = validator.errors();
		
		Assert.assertTrue(errors.isEmpty());
	}
	
	
	
	private final <T> void azzert(final ValidationError error, final Class<T> errorType, final String attributeName) {
		Assert.assertEquals(errorType.getClass(), errorType.getClass());
		Assert.assertEquals(error.getAttribute(), attributeName);
	}
}
