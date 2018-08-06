package com.iheart.challenge.response;

public class InternalServerError<T> extends Response<T> {
	
	public InternalServerError(final T message) {
		this.message = message;
	}
}
