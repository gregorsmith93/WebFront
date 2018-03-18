/**
 * @author Gregor Smith - 2018
 */
package com.webfront.controller.mapper;

import org.springframework.stereotype.Component;

import com.webfront.controller.model.UserControllerModel;
import com.webfront.domain.User;

/**
 * User Controller mapper.
 */
@Component
public interface UserControllerMapper {

	/**
	 * Maps User domain model into UserControllerModel.
	 *
	 * @param user
	 *            User
	 *
	 * @return UserControllerModel
	 */
	UserControllerModel map(User user);

	/**
	 * Maps UserControllerModel into User.
	 *
	 * @param userControllerModel
	 *            User Controller Model.
	 * 
	 * @return User
	 */
	User map(UserControllerModel userControllerModel);
}
