package com.iheart.challenge.validate;

public abstract class ValidationError {
	private String attribute;
	private String value;
	private String message;
	private String maxValue;
	
	public ValidationError() {
	}
	
	public ValidationError(final String attribute, final String value, final String message) {
		this.attribute = attribute;
		this.message = message;
		this.value = value;
	}
	
	public ValidationError(final String attribute, final String value, String maxValue, final String message) {
		this.attribute = attribute;
		this.message = message;
		this.value = value;
		this.maxValue = maxValue;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	
}
