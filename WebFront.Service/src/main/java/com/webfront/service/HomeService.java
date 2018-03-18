/**
 * @author Gregor Smith - 2018
 */
package com.webfront.service;

import org.springframework.stereotype.Service;

import com.webfront.domain.Home;

/**
 * Home service.
 */
@Service
public interface HomeService {

	/**
	 * Gets home.
	 *
	 * @return Home
	 */
	Home getHome();
}
