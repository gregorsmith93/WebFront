/**
 * @author Gregor Smith - 2018
 */
package com.webfront.integration;

import java.io.IOException;

import org.springframework.stereotype.Component;

/**
 * Handles json operations for api calls.
 */
@Component
public interface JsonIntegration {

	/**
	 * Convert a given object into the equivalent json string.
	 *
	 * @param content
	 *            Object to serialise.
	 *
	 * @return Json string.
	 *
	 * @throws IOException
	 */
	String convertObjectToJsonString(Object content) throws IOException;

	/**
	 * Retrieve an object of the given type from a json string.
	 *
	 * @param jsonString
	 *            Json string containing values.
	 * @param type
	 *            Type to deserialise to.
	 * 
	 * @return Object of the given type.
	 *
	 * @throws IOException
	 */
	<T> T getObjectFromJsonString(String jsonString, Class<T> type)
			throws IOException;
}
