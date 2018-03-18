/**
 * @author Gregor Smith - 2018
 */
package com.webfront.integration.mapper;

import org.springframework.stereotype.Component;

import com.webfront.domain.User;
import com.webfront.integration.model.UserIntegrationModel;

/**
 * Mapper for {@link UserIntegrationModel} and {@link User}.
 */
@Component
public interface UserIntegrationMapper {

	/**
	 * Maps from {@link UserIntegrationModel} to {@link User}.
	 *
	 * @param userIntegrationModel
	 *            User integration model.
	 *
	 * @return User.
	 */
	User map(UserIntegrationModel userIntegrationModel);

	/**
	 * Maps from {@link User} to {@link UserIntegrationModel}.
	 *
	 * @param user
	 *            User.
	 * 
	 * @return User integration model.
	 */
	UserIntegrationModel map(User user);
}
