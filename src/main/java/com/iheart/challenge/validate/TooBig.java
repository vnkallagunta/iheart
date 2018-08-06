package com.iheart.challenge.validate;

public class TooBig extends ValidationError{
	private TooBig(final String attribute, final String value, final String maxValue) {
		super(attribute, value, attribute+" exceeds maximum length/value allowed. Maximum length/value value. Maximum Allowed : "+maxValue);
	}
	
	public static final TooBig of(final String attribute, final String value, final String maxValue) {
		return new TooBig(attribute, value, maxValue);
	}
}
