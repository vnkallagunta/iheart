package com.iheart.challenge;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.util.ObjectUtils;

public interface BasicFunctions {
	default <T> void setIfNotNull(final Consumer<T> consumer, final Supplier<T> supplier) {
		if(!ObjectUtils.isEmpty(supplier.get())) {
			consumer.accept(supplier.get());
		}
	}
}
