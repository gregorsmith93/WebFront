/**
 * @author Gregor Smith - 2018
 */
package com.webfront.controller.mapper;

import org.springframework.stereotype.Component;

import com.webfront.controller.model.HomeControllerModel;
import com.webfront.domain.Home;

/**
 * Home controller model mapper.
 */
@Component
public interface HomeControllerModelMapper {

	/**
	 * Maps from Home domain model to Home Controller Model.
	 *
	 * @param home
	 *            Home domain model.
	 *
	 * @return HomeControllerModel
	 */
	HomeControllerModel map(Home home);

	/**
	 * Maps from Home Controller Model to Home domain model.
	 *
	 * @param homeControllerModel
	 *            Home Controller model.
	 * 
	 * @return Home
	 */
	Home map(HomeControllerModel homeControllerModel);
}
