package com.iheart.challenge;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public interface BasicFunctions {
	
	public static final Predicate<String> EMPTY_CHECK = (value) -> StringUtils.isEmpty(value);
	public static final BiPredicate<String, Integer> MAX_LENGTH_CHECK = (value, maxLength) -> (!StringUtils.isEmpty(value) && value.length() > maxLength);
	public static final Predicate<String> INVALID_CHARACTER_CHECK = (value) -> {
		if(EMPTY_CHECK.test(value)) {
			return false;
		}
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(value);
		boolean containsInvalidChar = m.find();
		return containsInvalidChar;
	};
	public static final Predicate<Double> NEGATIVE_AMOUNT_CHECK = (value) -> value != 0 && value < 0;
	public static final BiPredicate<Double, Double> MAX_AMOUNT_CHECK = (value, maxValue) -> value != 0 && value > maxValue;
	
	default <T> void setIfNotNull(final Consumer<T> consumer, final Supplier<T> supplier) {
		if(!ObjectUtils.isEmpty(supplier.get())) {
			consumer.accept(supplier.get());
		}
	}
}
