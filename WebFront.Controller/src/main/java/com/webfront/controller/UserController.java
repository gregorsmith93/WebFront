/**
 *
 * @author Gregor Smith - 2018
 *
 */
package com.webfront.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webfront.controller.mapper.UserControllerMapper;
import com.webfront.controller.model.UserControllerModel;
import com.webfront.domain.User;
import com.webfront.service.UserService;

/**
 * User controller.
 */
@RequestMapping(ControllerConstants.USER_PATH)
@Controller
public class UserController {

	/** Logger instance. */
	private final static Logger LOG = LogManager.getLogger(UserController.class);

	/** User service. */
	private final UserService userService;

	/** View loader. */
	private final ViewLoader viewLoader;

	/** User controller mapper. */
	private final UserControllerMapper userControllerMapper;

	/**
	 * Constructor.
	 *
	 * @param userService
	 *            User service.
	 * @param userControllerMapper
	 *            User controller mapper.
	 * @param viewLoader
	 *            View loader.
	 */
	@Autowired
	public UserController(final UserService userService,
			final UserControllerMapper userControllerMapper, final ViewLoader viewLoader) {
		this.userControllerMapper = userControllerMapper;
		this.userService = userService;
		this.viewLoader = viewLoader;
	}

	/**
	 * Loads the create user web page.
	 *
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView loadCreateUserPage() {

		LOG.debug("Loading create user page.");

		final ModelAndView modelAndView = viewLoader.loadView("createUser");

		final UserControllerModel userControllerModel = new UserControllerModel();

		modelAndView.addObject("userControllerModel", userControllerModel);

		LOG.debug("Create user page loaded.");

		return modelAndView;
	}

	/**
	 * Handles user creation request.
	 *
	 * @param userControllerModel
	 *            User to create.
	 *
	 * @return ModelAndView
	 */
	@RequestMapping(path = ControllerConstants.CREATE_PATH, method = RequestMethod.POST)
	public ModelAndView createUser(
			final UserControllerModel userControllerModel) {

		LOG.debug("Received request to create user {}", userControllerModel);

		final ModelAndView modelAndView = viewLoader.loadView("userCreationSuccess");

		final User userToCreate = userControllerMapper.map(userControllerModel);

		final User createdUser = userService.createUser(userToCreate);

		final UserControllerModel createdUserControllerModel = userControllerMapper
				.map(createdUser);

		modelAndView.addObject("createdUser", createdUserControllerModel);

		LOG.debug("Successfully created user, returning user creation success.");

		return modelAndView;
	}

	/**
	 * Loads the user login page.
	 *
	 * @return ModelAndView
	 */
	@RequestMapping(path = ControllerConstants.LOGIN_PATH, method = RequestMethod.GET)
	public ModelAndView getLoginPage() {

		LOG.debug("Loading login page.");

		final ModelAndView modelAndView = viewLoader.loadView("login");

		final UserControllerModel userControllerModel = new UserControllerModel();

		final UserControllerModel createdUser = new UserControllerModel();

		modelAndView.addObject("userControllerModel", userControllerModel);
		modelAndView.addObject("createdUser", createdUser);
		modelAndView.addObject("activeUser", false);

		LOG.debug("Login page loaded.");

		return modelAndView;
	}

	/**
	 * Handles login request.
	 *
	 * @param userControllerModel
	 *            User to login.
	 *
	 * @return ModelAndView
	 */
	@RequestMapping(path = ControllerConstants.LOGIN_PATH, method = RequestMethod.POST)
	public ModelAndView login(
			final UserControllerModel userControllerModel) {

		LOG.debug("Received login request for username: {}.", userControllerModel.getUsername());

		final ModelAndView modelAndView = viewLoader.loadView("login");

		final User userToLogin = userControllerMapper.map(userControllerModel);

		final User activeUser = userService.login(userToLogin);

		final UserControllerModel activeUserControllerModel = userControllerMapper
				.map(activeUser);

		modelAndView.addObject("userControllerModel",
				new UserControllerModel());
		modelAndView.addObject("createdUser", activeUserControllerModel);
		modelAndView.addObject("activeUser", true);

		LOG.debug("User {} successfully logged in.", activeUserControllerModel.getUsername());

		return modelAndView;
	}
}
