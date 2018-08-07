package com.iheart.challenge.validate;

import java.util.ArrayList;
import java.util.List;

import com.iheart.challenge.BasicFunctions;
import com.iheart.challenge.bean.Advertiser;

public class AdvertiserValidator implements BasicFunctions{
	
	public static final int NAME_MAX_LENGTH = 255;
	public static final int CONTACT_NAME_MAX_LENGTH = 255;
	public static final double MAX_CREDIT_LIMIT = 99999.99;
	
	public static final String NAME_ATTRIBUTE = "name";
	public static final String CONTACT_NAME_ATTRIBUTE = "contactName";
	public static final String CREDIT_LIMIT_ATTRIBUTE = "creditLimit";

	private Advertiser advertiser;
	
	private List<ValidationError> errors;
	
	
	
	private AdvertiserValidator(final Advertiser advertiser) {
		this.advertiser = advertiser;
		errors = new ArrayList<>();
	}
	
	public static final AdvertiserValidator of(final Advertiser advertiser) {
		return new AdvertiserValidator(advertiser);
	}
	
	public final AdvertiserValidator validateCreateAdvertiser() {
		this.nameEmpty()
			.contactNameEmpty()
			.nameExceedsMaxLength()
			.contactNameExceedsMaxLength()
			.validName()
			.validContactName()
			.negativeCreditLimit()
			.creditLimitExceeded()
			;
		return this;
	}
	
	public final AdvertiserValidator validateUpdateAdvertiser() {
		this.nameExceedsMaxLength()
			.contactNameExceedsMaxLength()
			.validName()
			.validContactName()
			.negativeCreditLimit()
			.creditLimitExceeded()
			;
		return this;
	}
	
	public AdvertiserValidator nameEmpty() {
		if(EMPTY_CHECK.test(advertiser.getName())) {
			errors.add(Empty.of(NAME_ATTRIBUTE, advertiser.getName()));
		}
		
		return this;
	}
	
	public AdvertiserValidator nameExceedsMaxLength() {
		if(MAX_LENGTH_CHECK.test(advertiser.getName(), NAME_MAX_LENGTH)) {
			errors.add(TooBig.of(NAME_ATTRIBUTE, advertiser.getName(), String.valueOf(NAME_MAX_LENGTH)));
		}
		return this;
	}
	
	public AdvertiserValidator validName() {
		if(INVALID_CHARACTER_CHECK.test(advertiser.getName())) {
			errors.add(InvalidValue.of(NAME_ATTRIBUTE, advertiser.getName()));
		}
		return this;
	}
	
	public AdvertiserValidator contactNameEmpty() {
		if(EMPTY_CHECK.test(advertiser.getContactName()) ) {
			errors.add(Empty.of(CONTACT_NAME_ATTRIBUTE, advertiser.getContactName()));
		}
		
		return this;
	}
	
	public AdvertiserValidator contactNameExceedsMaxLength() {
		if(MAX_LENGTH_CHECK.test(advertiser.getContactName(), CONTACT_NAME_MAX_LENGTH)) {
			errors.add(TooBig.of(CONTACT_NAME_ATTRIBUTE, advertiser.getContactName(), String.valueOf(CONTACT_NAME_MAX_LENGTH)));
		}
		return this;
	}
	
	public AdvertiserValidator validContactName() {
		if(INVALID_CHARACTER_CHECK.test(advertiser.getContactName())) {
			errors.add(InvalidValue.of(CONTACT_NAME_ATTRIBUTE, advertiser.getContactName()));
		}
		return this;
	}
	
	public AdvertiserValidator negativeCreditLimit() {
		if(NEGATIVE_AMOUNT_CHECK.test(advertiser.getCreditLimit())) {
			errors.add(InvalidValue.of(CREDIT_LIMIT_ATTRIBUTE, String.valueOf(advertiser.getCreditLimit())));
		}
		return this;
	}
	
	public AdvertiserValidator creditLimitExceeded() {
		if(MAX_AMOUNT_CHECK.test(advertiser.getCreditLimit(), MAX_CREDIT_LIMIT)) {
			errors.add(TooBig.of(CREDIT_LIMIT_ATTRIBUTE, String.valueOf(advertiser.getCreditLimit()), String.valueOf(MAX_CREDIT_LIMIT)));
		}
		return this;
	}
	
	public final boolean containsErrors() {
		return !errors.isEmpty();
	}
	
	public final List<ValidationError> errors() {
		return errors;
	}
}
