package com.webfront.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webfront.domain.Home;
import com.webfront.integration.HomeIntegration;

@Service
public class HomeServiceHandler implements HomeService{

	private static final Logger LOG = LogManager.getLogger(HomeServiceHandler.class);
	
	private final HomeIntegration homeIntegration;
	
	@Autowired
	public HomeServiceHandler(HomeIntegration homeIntegration) {
		this.homeIntegration = homeIntegration;
	}
	
	public Home getHome() {
		
		LOG.debug("Starting to load home.");
		
		Home home =  homeIntegration.getHome();
		
		LOG.debug("Finished loading home.");
		
		return home;
	}
}
