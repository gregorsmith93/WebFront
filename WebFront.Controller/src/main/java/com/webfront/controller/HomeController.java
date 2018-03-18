/**
 *
 * @author Gregor Smith - 2018
 *
 */
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

/**
 * Controller for homepage.
 */
@RequestMapping(ControllerConstants.HOME_PATH)
@Controller
public class HomeController {

	/** Logger instance. */
	private static final Logger LOG = LogManager
			.getLogger(HomeController.class);

	/** View loader. */
	private final ViewLoader viewLoader;

	/** Home service. */
	private final HomeService homeService;

	/** Home Controller Model mapper. */
	private final HomeControllerModelMapper homeControllerMapper;

	/**
	 * Constructor for Home Controller. Instances provided through Spring injection.
	 *
	 * @param homeService
	 *            Home service.
	 * @param homeControllerMapper
	 *            Home controller mapper.
	 * @param viewLoader
	 *            View loader.
	 */
	@Autowired
	public HomeController(final HomeService homeService,
			final HomeControllerModelMapper homeControllerMapper, final ViewLoader viewLoader) {
		this.homeService = homeService;
		this.homeControllerMapper = homeControllerMapper;
		this.viewLoader = viewLoader;
	}

	/**
	 * Loads the home page.
	 *
	 * @return Model and view showing homepage with welcome message.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView loadHomePage() {

		LOG.debug("Starting to load homepage.");

		final ModelAndView modelAndView = viewLoader.loadView("home");

		final List<HomeControllerModel> homeControllerModelList = new ArrayList<HomeControllerModel>();
		homeControllerModelList.add(loadHomeModel());

		modelAndView.addObject("homeControllerModelList",
				homeControllerModelList);

		LOG.debug("Finished loading homepage.");

		return modelAndView;
	}

	/**
	 * Internal load home message.
	 *
	 * @return HomeControllerModel
	 */
	private HomeControllerModel loadHomeModel() {

		final Home home = homeService.getHome();

		final HomeControllerModel homeControllerModel = homeControllerMapper
				.map(home);

		return homeControllerModel;
	}

	/**
	 * Loads a number of message requests.
	 *
	 * @param requestCount
	 *            Number of messages to return
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView loadMessageRequests(
			@RequestParam(value = "requestCount") final int requestCount) {

		LOG.debug("Loading {} messages.", requestCount);

		final ModelAndView modelAndView = viewLoader.loadView("home");

		final List<HomeControllerModel> homeControllerModelList = new ArrayList<HomeControllerModel>();

		for (int requestIndex = 0; requestIndex < requestCount; requestIndex++) {

			homeControllerModelList.add(loadHomeModel());
		}

		modelAndView.addObject("homeControllerModelList",
				homeControllerModelList);

		LOG.debug("{} messages loaded.", homeControllerModelList.size());

		return modelAndView;
	}
}
