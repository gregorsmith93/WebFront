/**
 * @author Gregor Smith - 2018
 */
package com.webfront.integration.mapper;

import org.springframework.stereotype.Component;

import com.webfront.domain.Home;
import com.webfront.integration.model.HomeIntegrationModel;

/**
 * Mapper for {@link HomeIntegrationModel} and {@link Home}
 */
@Component
public interface HomeIntegrationMapper {

	/**
	 * Maps {@link HomeIntegrationModel} to {@link Home}.
	 *
	 * @param homeIntegrationModel
	 *            Home integration model.
	 *
	 * @return Home
	 */
	Home map(HomeIntegrationModel homeIntegrationModel);

	/**
	 * Maps {@link Home} to {@link HomeIntegrationModel}.
	 *
	 * @param home
	 *            Home.
	 * 
	 * @return Home integration model.
	 */
	HomeIntegrationModel map(Home home);
}
