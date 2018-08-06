package com.iheart.challenge;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private ResourceNotFoundException(final String name) {
		super("Specified "+name+" not found.");
	}
	
	public static final ResourceNotFoundException of(final String resourceName) {
		return new ResourceNotFoundException(resourceName);
	}
}
