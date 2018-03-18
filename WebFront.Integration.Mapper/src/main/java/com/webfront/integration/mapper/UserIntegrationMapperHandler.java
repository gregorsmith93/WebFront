/**
 * @author Gregor Smith - 2018
 */
package com.webfront.integration.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.webfront.domain.User;
import com.webfront.integration.model.UserIntegrationModel;

/**
 * Implementation of {@link UserIntegrationMapper}
 */
@Component
public class UserIntegrationMapperHandler implements UserIntegrationMapper {

	/** Logger instance. */
	private static final Logger LOG = LogManager.getLogger(UserIntegrationMapperHandler.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User map(final UserIntegrationModel userIntegrationModel) {

		LOG.debug("Mapping UserIntegrationModel: {}", userIntegrationModel);

		final User user = new User();
		user.setName(userIntegrationModel.getName());
		user.setUsername(userIntegrationModel.getUsername());
		user.setPassword(userIntegrationModel.getPassword());
		user.setSalt(userIntegrationModel.getSalt());
		user.setUserId(userIntegrationModel.getUserId());

		LOG.debug("Mapped to User: {}", user);

		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserIntegrationModel map(final User user) {

		LOG.debug("Mapping User: {}", user);

		final UserIntegrationModel userIntegrationModel = new UserIntegrationModel();
		userIntegrationModel.setName(user.getName());
		userIntegrationModel.setUsername(user.getUsername());
		userIntegrationModel.setPassword(user.getPassword());
		userIntegrationModel.setSalt(user.getSalt());
		userIntegrationModel.setUserId(user.getUserId());

		LOG.debug("Mapped to UserIntegrationModel: {}", userIntegrationModel);

		return userIntegrationModel;
	}

}
