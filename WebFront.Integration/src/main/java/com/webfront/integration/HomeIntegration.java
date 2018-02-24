package com.webfront.integration;

import org.springframework.stereotype.Component;

import com.webfront.domain.Home;

@Component
public interface HomeIntegration {

	Home getHome();
}
