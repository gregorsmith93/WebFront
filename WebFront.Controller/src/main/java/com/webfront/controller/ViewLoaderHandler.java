/**
 *
 * @author Gregor Smith - 2018
 *
 */
package com.webfront.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

/**
 * Implementation of {@link ViewLoader}
 */
@Component
@SessionScope
public class ViewLoaderHandler implements ViewLoader {

	/** Logger instance. */
	private static final Logger LOG = LogManager
			.getLogger(ViewLoaderHandler.class);

	/** List of valid views. */
	private final List<String> viewList = new ArrayList<String>();

	/**
	 * Initialisation.
	 */
	@PostConstruct
	private void init() {

		LOG.debug("Initialising view loader.");

		final Package classPackage = ViewLoaderHandler.class.getPackage();

		final StringBuilder viewRootDirectoryPath = new StringBuilder();
		viewRootDirectoryPath.append(ControllerConstants.WEBAPPS)
				.append(classPackage.getImplementationTitle())
				.append(ControllerConstants.VIEW_DIRECTORY);

		final File viewRootDirectory = new File(viewRootDirectoryPath.toString());

		LOG.debug("Looking for views in directory: {}", viewRootDirectory.getAbsolutePath());

		final Collection<File> viewFileList = FileUtils.listFiles(
				viewRootDirectory, new String[] { "jsp" },
				true);

		viewFileList.forEach(file -> {
			final String fileName = FilenameUtils.removeExtension(file.getName());
			viewList.add(fileName);
			LOG.debug("View {} added to view list.", fileName);
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ModelAndView loadView(final String viewName) {

		LOG.debug("Attempting to load view {}.", viewName);

		ModelAndView modelAndView = null;

		if (viewList.contains(viewName.toLowerCase())) {
			modelAndView = new ModelAndView(viewName);
			LOG.debug("View {} is valid.", viewName);
		} else {
			final String errorMessage = String.format("Invalid view name: %s.",
					viewName);
			LOG.error(errorMessage);
			throw new IllegalStateException(errorMessage);
		}

		return modelAndView;
	}
}
