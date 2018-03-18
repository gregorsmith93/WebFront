/**
 * @author Gregor Smith - 2018
 */
package com.webfront.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webfront.domain.User;
import com.webfront.integration.UserIntegration;

/**
 * Implementation of {@link UserService}
 */
@Service
public class UserServiceHandler implements UserService {

	/** Logger instance. */
	private static final Logger LOG = LogManager.getLogger(UserServiceHandler.class);

	/** User integration. */
	private final UserIntegration userIntegration;

	/** Salt service, typed to {@link User} */
	private final SaltService<User> saltService;

	/** Hashing service. */
	private final HashingService hashingService;

	/**
	 * Constructor.
	 *
	 * @param userIntegration
	 *            User integration.
	 * @param saltService
	 *            Salt service.
	 * @param hashingService
	 *            Hashing service.
	 */
	@Autowired
	public UserServiceHandler(final UserIntegration userIntegration,
			final SaltService<User> saltService,
			final HashingService hashingService) {
		this.userIntegration = userIntegration;
		this.saltService = saltService;
		this.hashingService = hashingService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User createUser(final User user) {

		LOG.info("Setting salt for user {}", user.getUsername());

		user.setSalt(saltService.createSalt(user, User.class));

		LOG.info("Building password for user: {}", user.getUsername());

		user.setPassword(
				hashingService.buildPasswordHash(user.getPassword(),
						user.getSalt()));

		LOG.debug("Creating user: {}", user);

		final User createdUser = userIntegration.createUser(user);

		LOG.debug("User {} created.", createdUser.getUsername());

		return createdUser;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User login(final User user) {

		LOG.debug("Retrieving user {} from database.", user.getUsername());

		final User databaseUser = userIntegration
				.readUserByUsername(user.getUsername());

		LOG.debug("Building password for user: {}", user.getUsername());

		user.setPassword(hashingService.buildPasswordHash(user.getPassword(),
				databaseUser.getSalt()));

		LOG.debug("Performing login request for user: {}", user.getUsername());

		final User loggedInUser = userIntegration.login(user);

		LOG.debug("Successfully loggin in user: {}", loggedInUser.getUsername());

		return loggedInUser;
	}
}
