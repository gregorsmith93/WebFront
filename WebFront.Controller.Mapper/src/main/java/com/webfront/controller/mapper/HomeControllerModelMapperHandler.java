/**
 * @author Gregor Smith - 2018
 */
package com.webfront.controller.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.webfront.controller.model.HomeControllerModel;
import com.webfront.domain.Home;

/**
 * Implementation of {@link HomeControllerModelMapper}
 */
@Component
public class HomeControllerModelMapperHandler
		implements HomeControllerModelMapper {

	/** Logger instance. */
	private static final Logger LOG = LogManager
			.getLogger(HomeControllerModelMapperHandler.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HomeControllerModel map(final Home home) {

		LOG.debug("Mapping Home: {}", home);

		final HomeControllerModel homeControllerModel = new HomeControllerModel();

		homeControllerModel.setMessage(home.getMessage());

		LOG.debug("Mapped into HomeControllerModel {}", homeControllerModel);

		return homeControllerModel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Home map(final HomeControllerModel homeControllerModel) {

		LOG.debug("Mapping HomeControllerModel: {}", homeControllerModel);

		final Home home = new Home();

		home.setMessage(homeControllerModel.getMessage());

		LOG.debug("Mapped into Home: {}", home);

		return home;
	}
}
