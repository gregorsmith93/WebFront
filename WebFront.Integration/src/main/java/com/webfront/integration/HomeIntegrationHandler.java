/**
 * @author Gregor Smith - 2018
 */
package com.webfront.integration;

import java.io.IOException;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webfront.domain.Home;
import com.webfront.integration.mapper.HomeIntegrationMapper;
import com.webfront.integration.model.HomeIntegrationModel;

/**
 * Implementation of {@link HomeIntegration}
 */
@Component
public class HomeIntegrationHandler implements HomeIntegration {

	/** Logger instance. */
	private static final Logger LOG = LogManager
			.getLogger(HomeIntegrationHandler.class);

	/** Home integration mapper. */
	private final HomeIntegrationMapper homeIntegrationMapper;

	/** Client request. */
	private final ClientRequest clientRequest;

	/** Json integration. */
	private final JsonIntegration jsonIntegration;

	/**
	 * Constructor.
	 *
	 * @param homeIntegrationMapper
	 *            Home integration mapper.
	 * @param clientRequest
	 *            Client request.
	 * @param jsonIntegration
	 *            Json integration.
	 */
	@Autowired
	public HomeIntegrationHandler(
			final HomeIntegrationMapper homeIntegrationMapper,
			final ClientRequest clientRequest,
			final JsonIntegration jsonIntegration) {
		this.homeIntegrationMapper = homeIntegrationMapper;
		this.clientRequest = clientRequest;
		this.jsonIntegration = jsonIntegration;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Home getHome() {

		LOG.debug("Starting to get home.");

		final Response response = clientRequest.performClientRequest(
				IntegrationConstants.HOME_API_PATH, RequestMethod.GET, null,
				null);

		HomeIntegrationModel homeIntegrationModel = new HomeIntegrationModel();

		if (response.getStatus() == 200) {

			response.bufferEntity();
			final String jsonString = response.readEntity(String.class);

			try {
				homeIntegrationModel = jsonIntegration.getObjectFromJsonString(
						jsonString, HomeIntegrationModel.class);
			} catch (final IOException e) {
				throw new InternalServerErrorException(e);
			}
		} else {
			throw new InternalServerErrorException("Response was not 200-OK.");
		}

		LOG.debug("Finished getting home.");

		return homeIntegrationMapper.map(homeIntegrationModel);
	}
}
