package com.iheart.challenge.logging;

import org.slf4j.Logger;

public interface StepLogger {
	
	Logger getLogger();
	
	default void logStep(final String message) {
		getLogger().debug("step:" + message);
	}
	
	default void logResourceStep(final String message) {
		getLogger().debug("Resource: "+message);
	}
	
	default void logResourceStepComplete(final String message) {
		getLogger().debug("Resource: Done:"+message);
	}
	
	default void logServiceStep(final String message) {
		getLogger().debug("Service: "+message);
	}
	
	default void logServiceStepComplete(final String message) {
		getLogger().debug("Service: Done: "+message);
	}
	
	default void debug(final String message) {
		getLogger().debug(message);
	}
	
	default void info(final String message) {
		getLogger().info(message);
	}
	
	default void error(final String message) {
		getLogger().error(message);
	}
	
	default void error(final String message, final Exception e) {
		getLogger().error(message, e);
	}
}
