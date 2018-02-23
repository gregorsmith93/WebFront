package com.webfront.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceHandler implements HomeService{

	private static final Logger LOG = LogManager.getLogger(HomeServiceHandler.class);
	
	public String getHomeMessage() {
		
		LOG.debug("Starting to load home message.");
		
		String message = "Fuck the world!";
		
		LOG.debug("Finished loading home message.");
		
		return message;
	}
}
