package com.iheart.challenge.validate;

public class Empty extends ValidationError{
	
	private Empty(final String attribute, final String value) {
		super(attribute, value, attribute+" is required.");
	}
	
	public static final Empty of(final String attribute, final String value) {
		return new Empty(attribute, value);
	}
}
