/**
 * @author Gregor Smith - 2018
 */
package com.webfront.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link ConfigurationLoader}
 */
@Component
@PropertySource("classpath:webfront.properties")
public class ConfigurationLoaderHandler implements ConfigurationLoader {

	/**
	 * Internal context path.
	 */
	@Value("${webback.context.path}")
	private String webBackContextPath;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getWebBackContextPath() {
		return webBackContextPath;
	}
}
