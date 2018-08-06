package com.iheart.challenge.response;

import java.io.Serializable;

public class Response<T> implements Serializable{
	protected T message;
	
	public T getMessage() {
		return message;
	}
}
