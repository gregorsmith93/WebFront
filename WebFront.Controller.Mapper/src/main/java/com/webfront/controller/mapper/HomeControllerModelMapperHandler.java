package com.webfront.controller.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.webfront.controller.model.HomeControllerModel;
import com.webfront.domain.Home;

@Component
public class HomeControllerModelMapperHandler implements HomeControllerModelMapper {

	private static final Logger LOG = LogManager.getLogger(HomeControllerModelMapperHandler.class);
	
	public HomeControllerModel map(Home home) {
		
		LOG.debug("Mapping Home domain model to Home Controller Model.");
		
		HomeControllerModel homeControllerModel = new HomeControllerModel();
		
		homeControllerModel.setMessage(home.getMessage());
		
		LOG.debug("Finished mapping Home domain to Home controller.");
		
		return homeControllerModel;
	}
	
	public Home map(HomeControllerModel homeControllerModel) {
		
		LOG.debug("Mapping Home Controller model to Home domain.");
		
		Home home = new Home();
		
		home.setMessage(homeControllerModel.getMessage());
		
		LOG.debug("Finished mapping Home Controller model to Home domain.");
		
		return home;
	}
}
