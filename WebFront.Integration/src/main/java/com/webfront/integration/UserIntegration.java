/**
 * @author Gregor Smith - 2018
 */
package com.webfront.integration;

import org.springframework.stereotype.Component;

import com.webfront.domain.User;

/**
 * Handles integration calls for user services.
 */
@Component
public interface UserIntegration {

	/**
	 * Creates a user.
	 *
	 * @param user
	 *            User to create.
	 *
	 * @return Created user.
	 */
	User createUser(User user);

	/**
	 * Retrieves a user given a username.
	 *
	 * @param username
	 *            Username.
	 *
	 * @return User
	 */
	User readUserByUsername(String username);

	/**
	 * Performs a login request for the given user.
	 *
	 * @param user
	 *            User to login.
	 * 
	 * @return Logged in user.
	 */
	User login(User user);
}
