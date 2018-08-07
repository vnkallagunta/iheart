package com.iheart.challenge.utils;

import com.iheart.challenge.BasicFunctions;

public interface Exceptions extends BasicFunctions{
	default void throwRuntimeExceptionOnEmpty(final String value, final String message, Class<?> type) {
		if(EMPTY_CHECK.test(value)) {
				try {
				 RuntimeException e = (RuntimeException)type.newInstance();
				 throw e;
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		}
	}
}
