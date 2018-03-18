/**
 * @author Gregor Smith - 2018
 */
package com.webfront.controller.model;

/**
 * Home Controller Model.
 */
public class HomeControllerModel {

	/** Message. */
	private String message;

	/** Request count. */
	private int requestCount;

	/**
	 * Constructor.
	 *
	 * @param message
	 *            Message.
	 */
	public HomeControllerModel(final String message) {
		this.message = message;
	}

	/**
	 * Constructor.
	 *
	 * @param requestCount
	 *            Request count.
	 */
	public HomeControllerModel(final int requestCount) {
		this.requestCount = requestCount;
	}

	/** Constructor. */
	public HomeControllerModel() {

	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public void setRequestCount(final int requestCount) {
		this.requestCount = requestCount;
	}

	public int getRequestCount() {
		return this.requestCount;
	}
}
