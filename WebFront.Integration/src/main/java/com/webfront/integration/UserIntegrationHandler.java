/**
 * @author Gregor Smith - 2018
 */
package com.webfront.integration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webfront.domain.User;
import com.webfront.integration.mapper.UserIntegrationMapper;
import com.webfront.integration.model.UserIntegrationModel;

/**
 * Implementation of {@link UserIntegration}
 */
@Component
public class UserIntegrationHandler implements UserIntegration {

	/** Logger instance. */
	private static final Logger LOG = LogManager
			.getLogger(UserIntegrationHandler.class);

	/** User integration mapper. */
	private final UserIntegrationMapper userIntegrationMapper;

	/** Client request. */
	private final ClientRequest clientRequest;

	/** Json integration. */
	private final JsonIntegration jsonIntegration;

	/**
	 * Constructor.
	 *
	 * @param userIntegrationMapper
	 *            User integration mapper.
	 * @param clientRequest
	 *            Client request.
	 * @param jsonIntegration
	 *            Json integration.
	 */
	@Autowired
	public UserIntegrationHandler(
			final UserIntegrationMapper userIntegrationMapper,
			final ClientRequest clientRequest,
			final JsonIntegration jsonIntegration) {
		this.userIntegrationMapper = userIntegrationMapper;
		this.clientRequest = clientRequest;
		this.jsonIntegration = jsonIntegration;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User createUser(final User user) {

		LOG.debug("Performing create user request for user: {}", user);

		final UserIntegrationModel userIntegrationModel = userIntegrationMapper
				.map(user);

		final Response response = clientRequest.performClientRequest(
				IntegrationConstants.USER_API_PATH, RequestMethod.POST, null,
				userIntegrationModel);

		UserIntegrationModel userResponse = new UserIntegrationModel();

		if (response.getStatus() == 200) {

			LOG.debug("Recieved 200-OK request from create user request.");

			response.bufferEntity();
			final String jsonString = response.readEntity(String.class);

			try {
				userResponse = jsonIntegration.getObjectFromJsonString(
						jsonString, UserIntegrationModel.class);
			} catch (final IOException e) {
				throw new InternalServerErrorException(e);
			}
		} else {
			LOG.warn("Response was not 200-OK, response status was {}", response.getStatus());
			throw new InternalServerErrorException("Response was not 200-OK.");
		}

		return userIntegrationMapper.map(userResponse);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User readUserByUsername(final String username) {

		LOG.debug("Performing read request for username: {}", username);

		final Map<String, String> queryParams = new HashMap<String, String>();

		queryParams.put(IntegrationConstants.USERNAME_QUERY_PARAM, username);

		final Response response = clientRequest.performClientRequest(
				IntegrationConstants.READ_USER_API_PATH, RequestMethod.GET,
				queryParams, null);

		UserIntegrationModel userResponse = new UserIntegrationModel();

		if (response.getStatus() == 200) {

			LOG.debug("Recieved 200-OK request from read user request.");

			response.bufferEntity();
			final String jsonString = response.readEntity(String.class);

			try {
				userResponse = jsonIntegration.getObjectFromJsonString(
						jsonString, UserIntegrationModel.class);
			} catch (final IOException e) {
				throw new InternalServerErrorException(e);
			}
		} else {
			LOG.warn("Response was not 200-OK, response status was {}", response.getStatus());
			throw new InternalServerErrorException("Response was not 200-OK.");
		}

		return userIntegrationMapper.map(userResponse);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User login(final User user) {

		LOG.debug("Performing login request for username: {}", user.getUsername());

		final UserIntegrationModel userIntegrationModel = userIntegrationMapper
				.map(user);

		final Response response = clientRequest.performClientRequest(
				IntegrationConstants.LOGIN_USER_API_PATH, RequestMethod.POST,
				null,
				userIntegrationModel);

		UserIntegrationModel userResponse = new UserIntegrationModel();

		if (response.getStatus() == 200) {

			LOG.debug("Recieved 200-OK request from read user request.");

			response.bufferEntity();
			final String jsonString = response.readEntity(String.class);

			try {
				userResponse = jsonIntegration.getObjectFromJsonString(
						jsonString, UserIntegrationModel.class);
			} catch (final IOException e) {
				throw new InternalServerErrorException(e);
			}
		} else {
			LOG.warn("Response was not 200-OK, response status was {}", response.getStatus());
			throw new InternalServerErrorException("Response was not 200-OK.");
		}

		return userIntegrationMapper.map(userResponse);
	}
}
