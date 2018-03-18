/**
 * @author Gregor Smith - 2018
 */
package com.webfront.domain;

/**
 * Home.
 */
public class Home {

	/** Message. */
	private String message;

	/**
	 * Constructor.
	 *
	 * @param message
	 *            Message
	 */
	public Home(final String message) {
		this.message = message;
	}

	/**
	 * Constructor.
	 */
	public Home() {

	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}
}
