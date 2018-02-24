package com.webfront.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webfront.controller.mapper.HomeControllerModelMapper;
import com.webfront.controller.model.HomeControllerModel;
import com.webfront.domain.Home;
import com.webfront.service.HomeService;

@RequestMapping(ControllerConstants.HOME_PATH)
@Controller
public class HomeController {
	
	private static final Logger LOG = LogManager.getLogger(HomeController.class);
	
	private final HomeService homeService;
	
	private final HomeControllerModelMapper homeControllerMapper;
	
	@Autowired
	public HomeController(HomeService homeService, HomeControllerModelMapper homeControllerMapper) {
		this.homeService = homeService;
		this.homeControllerMapper = homeControllerMapper;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView loadHomePage() {
		
		LOG.debug("Starting to load homepage.");
		
		final ModelAndView modelAndView = new ModelAndView("home");
		
		List<HomeControllerModel> homeControllerModelList = new ArrayList<HomeControllerModel>();
		homeControllerModelList.add(loadHomeModel());
		
		modelAndView.addObject("homeControllerModelList", homeControllerModelList);
		
		LOG.debug("Finished loading homepage.");
		
		return modelAndView;
	}
	
	private HomeControllerModel loadHomeModel() {
		
		Home home = homeService.getHome();
		
		HomeControllerModel homeControllerModel = homeControllerMapper.map(home);
		
		return homeControllerModel;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView loadMessageRequests(@RequestParam(value = "requestCount") int requestCount) {
		
		final ModelAndView modelAndView = new ModelAndView("home");
		
		List<HomeControllerModel> homeControllerModelList = new ArrayList<HomeControllerModel>();
		
		for (int requestIndex = 0; requestIndex < requestCount; requestIndex++) {
			
			homeControllerModelList.add(loadHomeModel());
		}
		
		modelAndView.addObject("homeControllerModelList", homeControllerModelList);
		return modelAndView;
	}
}
