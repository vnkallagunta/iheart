package com.iheart.challenge.response;

public class BadRequest <T> extends Response<T> {
	public BadRequest(final T message) {
		this.message = message;
	}
}
