package com.webfront.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webfront.controller.model.HomeControllerModel;
import com.webfront.service.HomeService;

@RequestMapping(ControllerConstants.HOME_PATH)
@Controller
public class HomeController {
	
	private static final Logger LOG = LogManager.getLogger(HomeController.class);
	
	private final HomeService homeService;
	
	@Autowired
	public HomeController(HomeService homeService) {
		this.homeService = homeService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView loadHomePage() {
		
		LOG.debug("Starting to load homepage.");
		
		final ModelAndView modelAndView = new ModelAndView("home");
		
		String message = homeService.getHomeMessage();
		
		HomeControllerModel homeControllerModel = new HomeControllerModel(message);
		
		modelAndView.addObject("homeControllerModel", homeControllerModel);
		
		LOG.debug("Finished loading homepage.");
		
		return modelAndView;
	}
}
