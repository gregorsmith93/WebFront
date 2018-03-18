/**
 *
 * @author Gregor Smith - 2018
 *
 */
package com.webfront.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

/**
 * Interface for view loading.
 */
@Component
@SessionScope
public interface ViewLoader {

	/**
	 * Create an instance of ModelAndView given a view name.
	 *
	 * @param viewName
	 *            View to load.
	 * @return ModelAndView
	 */
	ModelAndView loadView(String viewName);
}
