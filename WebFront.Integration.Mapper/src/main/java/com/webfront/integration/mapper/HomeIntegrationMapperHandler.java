/**
 * @author Gregor Smith - 2018
 */
package com.webfront.integration.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.webfront.domain.Home;
import com.webfront.integration.model.HomeIntegrationModel;

/**
 * Implementation of {@link HomeIntegrationMapper}
 */
@Component
public class HomeIntegrationMapperHandler implements HomeIntegrationMapper {

	/** Logger instance. */
	private static final Logger LOG = LogManager
			.getLogger(HomeIntegrationMapperHandler.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Home map(final HomeIntegrationModel homeIntegrationModel) {

		LOG.debug("Mapping HomeIntegrationModel: {}", homeIntegrationModel);

		final Home home = new Home();

		home.setMessage(homeIntegrationModel.getMessage());

		LOG.debug("Mapped to Home: {}", home);

		return home;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HomeIntegrationModel map(final Home home) {

		LOG.debug("Mapping Home: {}", home);

		final HomeIntegrationModel homeIntegrationModel = new HomeIntegrationModel();

		homeIntegrationModel.setMessage(home.getMessage());

		LOG.debug("Mapped to HomeIntegrationModel: {}", homeIntegrationModel);

		return homeIntegrationModel;
	}
}
