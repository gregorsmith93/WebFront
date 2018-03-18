package com.webfront.config;

import org.springframework.stereotype.Component;

@Component
public interface ConfigurationLoader {

	String getWebBackContextPath();
}
