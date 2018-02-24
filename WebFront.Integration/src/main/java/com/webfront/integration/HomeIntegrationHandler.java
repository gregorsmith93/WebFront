package com.webfront.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webfront.domain.Home;
import com.webfront.integration.mapper.HomeIntegrationMapper;
import com.webfront.integration.model.HomeIntegrationModel;

@Component
public class HomeIntegrationHandler implements HomeIntegration{

	private static final Logger LOG = LogManager.getLogger(HomeIntegrationHandler.class);
	
	private final HomeIntegrationMapper homeIntegrationMapper;
	
	@Autowired
	public HomeIntegrationHandler(HomeIntegrationMapper homeIntegrationMapper) {
		this.homeIntegrationMapper = homeIntegrationMapper;
	}
	
	public Home getHome() {
		
		LOG.debug("Starting to get home.");
		
		String message = "Fuck me, java is so verbose.";
		
		HomeIntegrationModel homeIntegrationModel = new HomeIntegrationModel(message);
		
		LOG.debug("Finished getting home.");
		
		return homeIntegrationMapper.map(homeIntegrationModel);
	}
}
