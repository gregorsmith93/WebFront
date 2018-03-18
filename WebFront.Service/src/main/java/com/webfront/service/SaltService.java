/**
 * @author Gregor Smith - 2018
 */
package com.webfront.service;

import org.springframework.stereotype.Service;

/**
 * Salt service.
 *
 * @param <T>
 *            Type used to generate salt.
 */
@Service
public interface SaltService<T> {

	/**
	 * Creates a salt, given an object and its type.
	 *
	 * @param object
	 *            Object to create salt from.
	 * @param type
	 *            Type of object.
	 * 
	 * @return Salt.
	 */
	String createSalt(T object, Class<T> type);
}
