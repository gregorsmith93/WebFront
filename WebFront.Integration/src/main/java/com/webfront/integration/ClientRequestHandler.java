/**
 * @author Gregor Smith - 2018
 */
package com.webfront.integration;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webfront.config.ConfigurationLoader;

/**
 * Implementation of {@link ClientRequest}
 */
@Component
public class ClientRequestHandler implements ClientRequest {

	/** Logger instance. */
	private static final Logger LOG = LogManager
			.getLogger(ClientRequestHandler.class);

	/** Json integration. */
	private final JsonIntegration jsonIntegration;

	/** Configuration loader. */
	private final ConfigurationLoader configurationLoader;

	/**
	 * Constructor.
	 *
	 * @param jsonIntegration
	 *            Json integration.
	 * @param configurationLoader
	 *            Configuration loader.
	 */
	@Autowired
	public ClientRequestHandler(final JsonIntegration jsonIntegration, final ConfigurationLoader configurationLoader) {
		this.jsonIntegration = jsonIntegration;
		this.configurationLoader = configurationLoader;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response performClientRequest(final String path,
			final RequestMethod requestMethod,
			final Map<String, String> queryParams, final Object content) {

		final String configPath = configurationLoader.getWebBackContextPath();

		final String clientPath = new StringBuilder(configPath).append(path)
				.toString();

		LOG.debug(String.format("Starting to perform %s request for path: %s",
				requestMethod.name(), clientPath));

		final Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(clientPath);

		if (null != queryParams) {
			for (final Map.Entry<String, String> entry : queryParams
					.entrySet()) {
				LOG.debug("Adding query parameter: {}={}", entry.getKey(), entry.getValue());
				webTarget = webTarget.queryParam(entry.getKey(),
						entry.getValue());
			}
		}

		// Build empty response
		Response response = Response.noContent().build();

		if (requestMethod.equals(RequestMethod.GET)) {
			response = webTarget.request()
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.get(Response.class);
		} else if (requestMethod.equals(RequestMethod.POST)) {

			String jsonString;
			try {
				jsonString = jsonIntegration.convertObjectToJsonString(content);
			} catch (final IOException e) {
				throw new InternalServerErrorException(e);
			}

			response = webTarget.request()
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.post(Entity.json(jsonString));
		} else {
			final String errorMessage = String.format(
					"Request method type: %s is currently unsupported.",
					requestMethod.name());
			LOG.error(errorMessage);
			throw new InternalServerErrorException(errorMessage);
		}

		LOG.debug(String.format(
				"Finished performing %s request for path: %s. Response status was %d.",
				requestMethod.name(), clientPath, response.getStatus()));

		return response;
	}
}
