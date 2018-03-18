/**
 * @author Gregor Smith - 2018
 */
package com.webfront.integration;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link JsonIntegration}
 */
@Component
public class JsonIntegrationHandler implements JsonIntegration {

	/** Logger instance. */
	private static final Logger LOG = LogManager
			.getLogger(JsonIntegrationHandler.class);

	/** Object mapper. */
	private final ObjectMapper mapper;

	/** Constructor. */
	public JsonIntegrationHandler() {
		this.mapper = new ObjectMapper();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String convertObjectToJsonString(final Object content)
			throws IOException {

		LOG.debug("Starting to convert object of type {} into Json string.",
				content.getClass());

		String jsonString = null;

		try {
			jsonString = mapper.writeValueAsString(content);
		} catch (final IOException e) {
			final String errorMessage = String.format(
					"An error occured while converting object of type %s into Json string.",
					content.getClass());

			LOG.debug(errorMessage);
			throw new IOException(errorMessage, e);
		}

		LOG.debug("Finished converting object of type {} into Json string: {}",
				content.getClass(), jsonString);

		return jsonString;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T getObjectFromJsonString(final String jsonString,
			final Class<T> clazz) throws IOException {

		LOG.debug("Starting to get object of type {} from Json string: {}",
				clazz.getName(), jsonString);

		T object;

		try {
			object = mapper.readValue(jsonString, clazz);
		} catch (final IOException e) {
			final String errorMessage = String.format(
					"An error occured while converting object of type %s from Json string.",
					clazz.getName());
			LOG.error(errorMessage);
			throw new IOException(errorMessage, e);
		}

		LOG.debug("Finished getting object of type {} from Json string.",
				clazz.getName());

		return object;
	}
}
