/**
 * @author Gregor Smith - 2018
 */
package com.webfront.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webfront.domain.Home;
import com.webfront.integration.HomeIntegration;

/**
 * Implementation of {@link HomeService}
 */
@Service
public class HomeServiceHandler implements HomeService {

	/** Logger instance. */
	private static final Logger LOG = LogManager
			.getLogger(HomeServiceHandler.class);

	/** Home integration. */
	private final HomeIntegration homeIntegration;

	/**
	 * Constructor.
	 *
	 * @param homeIntegration
	 *            Home integration.
	 */
	@Autowired
	public HomeServiceHandler(final HomeIntegration homeIntegration) {
		this.homeIntegration = homeIntegration;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Home getHome() {

		LOG.debug("Starting to load home.");

		final Home home = homeIntegration.getHome();

		LOG.debug("Finished loading home: {}", home);

		return home;
	}
}
