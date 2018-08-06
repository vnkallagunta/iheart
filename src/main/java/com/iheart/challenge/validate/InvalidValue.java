package com.iheart.challenge.validate;

public class InvalidValue extends ValidationError{
	private InvalidValue(final String attribute, final String value) {
		super(attribute, value, attribute+" value is invalid. Please check for special characters/negative values/allowed valid values.");
	}
	
	public static final InvalidValue of(final String attribute, final String value) {
		return new InvalidValue(attribute, value);
	}
}
