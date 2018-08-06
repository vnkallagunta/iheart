package com.iheart.challenge.response;

public class NotFound<T> extends Response<T>{
	public NotFound(final T value) {
		this.message= value;
	}
}
