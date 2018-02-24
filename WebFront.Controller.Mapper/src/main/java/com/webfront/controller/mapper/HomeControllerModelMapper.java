package com.webfront.controller.mapper;

import org.springframework.stereotype.Component;

import com.webfront.controller.model.HomeControllerModel;
import com.webfront.domain.Home;

@Component
public interface HomeControllerModelMapper {

	HomeControllerModel map(Home home);
	
	Home map(HomeControllerModel homeControllerModel);
}
