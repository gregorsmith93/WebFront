/**
 * @author Gregor Smith - 2018
 */
package com.webfront.integration.model;

/**
 * User integration model.
 */
public class UserIntegrationModel {

	/** User id. */
	private long userId;

	/** Name. */
	private String name;

	/** Username. */
	private String username;

	/** Password. */
	private String password;

	/** Salt. */
	private String salt;

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setSalt(final String salt) {
		this.salt = salt;
	}

	public String getSalt() {
		return this.salt;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(final long userId) {
		this.userId = userId;
	}
}
