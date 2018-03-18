/**
 * @author Gregor Smith - 2018
 */
package com.webfront.integration.model;

/**
 * Home integration model.
 */
public class HomeIntegrationModel {

	/** Message. */
	private String message;

	/**
	 * Constructor.
	 *
	 * @param message
	 *            Message.
	 */
	public HomeIntegrationModel(final String message) {
		this.message = message;
	}

	/**
	 * Constructor.
	 */
	public HomeIntegrationModel() {

	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
