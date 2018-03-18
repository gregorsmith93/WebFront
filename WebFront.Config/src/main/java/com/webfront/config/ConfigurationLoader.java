/**
 * @author Gregor Smith - 2018
 */
package com.webfront.config;

import org.springframework.stereotype.Component;

/**
 * Loads configuration properties.
 */
@Component
public interface ConfigurationLoader {

	/**
	 * Retrieve context path for API.
	 *
	 * @return Context path.
	 */
	String getWebBackContextPath();
}
