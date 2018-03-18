/**
 * @author Gregor Smith - 2018
 */
package com.webfront.integration;

import org.springframework.stereotype.Component;

import com.webfront.domain.Home;

/**
 * Home integration service.
 */
@Component
public interface HomeIntegration {

	/**
	 * Performs a request to the API for home message.
	 *
	 * @return Home
	 */
	Home getHome();
}
