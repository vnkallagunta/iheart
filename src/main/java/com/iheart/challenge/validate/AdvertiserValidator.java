package com.iheart.challenge.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.iheart.challenge.bean.Advertiser;

public class AdvertiserValidator {
	
	public static final int NAME_MAX_LENGTH = 255;
	public static final int CONTACT_NAME_MAX_LENGTH = 255;
	public static final double MAX_CREDIT_LIMIT = 99999.99;
	
	public static final String NAME_ATTRIBUTE = "name";
	public static final String CONTACT_NAME_ATTRIBUTE = "contactName";
	public static final String CREDIT_LIMIT_ATTRIBUTE = "creditLimit";

	private Advertiser advertiser;
	
	private List<ValidationError> errors;
	
	public static final Predicate<String> EMPTY_CHECK = (value) -> StringUtils.isEmpty(value);
	public static final BiPredicate<String, Integer> MAX_LENGTH_CHECK = (value, maxLength) -> (!StringUtils.isEmpty(value) && value.length() > maxLength);
	public static final Predicate<String> INVALID_CHARACTER_CHECK = (value) -> {
		if(EMPTY_CHECK.test(value)) {
			return false;
		}
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(value);
		boolean containsInvalidChar = m.find();
		return containsInvalidChar;
	};
	public static final Predicate<Double> NEGATIVE_AMOUNT_CHECK = (value) -> value != 0 && value < 0;
	public static final Predicate<Double> MAX_AMOUNT_CHECK = (value) -> value != 0 && value > MAX_CREDIT_LIMIT;
	
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
		if(MAX_AMOUNT_CHECK.test(advertiser.getCreditLimit())) {
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
