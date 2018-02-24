package com.webfront.integration.mapper;

import org.springframework.stereotype.Component;

import com.webfront.domain.Home;
import com.webfront.integration.model.HomeIntegrationModel;

@Component
public interface HomeIntegrationMapper {

	Home map(HomeIntegrationModel homeIntegrationModel);
	
	HomeIntegrationModel map(Home home);
}
