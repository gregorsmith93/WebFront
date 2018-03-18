/**
 * @author Gregor Smith - 2018
 */
package com.webfront.service;

import org.springframework.stereotype.Service;

import com.webfront.domain.User;

/**
 * User service.
 */
@Service
public interface UserService {

	/**
	 * Creates a user.
	 *
	 * @param user
	 *            The user to create.
	 * 
	 * @return Created user.
	 */
	User createUser(User user);

	/**
	 * Handles a login request.
	 *
	 * @param user
	 *            User to login.
	 * 
	 * @return Logged in user.
	 */
	User login(User user);
}
