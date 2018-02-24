package com.webfront.integration.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.webfront.domain.Home;
import com.webfront.integration.model.HomeIntegrationModel;

@Component
public class HomeIntegrationMapperHandler implements HomeIntegrationMapper{

	private static final Logger LOG = LogManager.getLogger(HomeIntegrationMapperHandler.class);
	
	public Home map(HomeIntegrationModel homeIntegrationModel) {
		
		LOG.debug("Starting to map Home Integration model to domain.");
		
		Home home = new Home();
		
		home.setMessage(homeIntegrationModel.getMessage());
		
		LOG.debug("Finished mapping Home Integration model to domain.");
		
		return home;
	}
	
	public HomeIntegrationModel map(Home home) {
		
		LOG.debug("Starting to map Home domain to integration model.");
		
		HomeIntegrationModel homeIntegrationModel = new HomeIntegrationModel();
		
		homeIntegrationModel.setMessage(home.getMessage());
		
		LOG.debug("Finished mapping home domain to integration model.");
		
		return homeIntegrationModel;
	}
}
