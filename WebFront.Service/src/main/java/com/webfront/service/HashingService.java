/**
 * @author Gregor Smith - 2018
 */
package com.webfront.service;

import org.springframework.stereotype.Service;

/**
 * Hashing service.
 */
@Service
public interface HashingService {

	/**
	 * Builds a hash for passwords.
	 *
	 * @param password
	 *            Password to hash.
	 * @param salt
	 *            Salt for password.
	 * 
	 * @return Hashed password.
	 */
	String buildPasswordHash(String password, String salt);
}
