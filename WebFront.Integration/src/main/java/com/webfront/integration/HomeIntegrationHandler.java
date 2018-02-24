package com.webfront.integration;

import java.io.IOException;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.webfront.domain.Home;
import com.webfront.integration.mapper.HomeIntegrationMapper;
import com.webfront.integration.model.HomeIntegrationModel;

@Component
public class HomeIntegrationHandler implements HomeIntegration{

	private static final Logger LOG = LogManager.getLogger(HomeIntegrationHandler.class);
	
	private final HomeIntegrationMapper homeIntegrationMapper;
	
	@Autowired
	public HomeIntegrationHandler(HomeIntegrationMapper homeIntegrationMapper) {
		this.homeIntegrationMapper = homeIntegrationMapper;
	}
	
	public Home getHome() {
		
		LOG.debug("Starting to get home.");
		
		Client client = ClientBuilder.newClient();
		
		String path = new StringBuilder(IntegrationConstants.HOME_API_PATH).toString();
		
		WebTarget webTarget = client.target(path);
		
		Response response = webTarget.request().accept(MediaType.APPLICATION_JSON_VALUE).get(Response.class);

		HomeIntegrationModel homeIntegrationModel = new HomeIntegrationModel();
		
		if (response.getStatus() == 200) {
			
			response.bufferEntity();
			String jsonString = response.readEntity(String.class);
			
			ObjectMapper mapper = new ObjectMapper();
			
			try {
			    homeIntegrationModel = mapper.readValue(jsonString, HomeIntegrationModel.class);
			} catch(IOException e) {
				throw new InternalServerErrorException(e);
			}
		}
		else {
			throw new InternalServerErrorException("Response was not 200-OK.");
		}
		
		LOG.debug("Finished getting home.");
		
		return homeIntegrationMapper.map(homeIntegrationModel);
	}
}
