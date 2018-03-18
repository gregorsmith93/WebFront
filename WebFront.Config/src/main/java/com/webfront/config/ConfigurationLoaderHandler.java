package com.webfront.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:webfront.properties")
public class ConfigurationLoaderHandler implements ConfigurationLoader {

	@Value("${webback.context.path}")
	private String webBackContextPath;

	public String getWebBackContextPath() {
		return webBackContextPath;
	}
}
