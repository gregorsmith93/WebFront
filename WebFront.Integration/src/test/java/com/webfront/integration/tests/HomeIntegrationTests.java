package com.webfront.integration.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnitRunner;

import com.webfront.domain.Home;
import com.webfront.integration.HomeIntegrationHandler;
import com.webfront.integration.mapper.HomeIntegrationMapper;
import com.webfront.integration.model.HomeIntegrationModel;

@RunWith(MockitoJUnitRunner.class)
public class HomeIntegrationTests{

	private static final String EXPECTED_MESSAGE = "";
	
	@InjectMocks private HomeIntegrationHandler homeIntegrationHandler;
	
	@Mock private HomeIntegrationMapper homeIntegrationMapper;
	
	@Test
	public void getHomeTest() {
		
		// Setup
		Home expectedHome = new Home();
		expectedHome.setMessage(EXPECTED_MESSAGE);
		
		// Mocks
		Mockito.when(homeIntegrationMapper.map(any(HomeIntegrationModel.class))).thenReturn(expectedHome);
		
		// Test
		Home actualHome = homeIntegrationHandler.getHome();
		
		// Assertion
		assertEquals(expectedHome, actualHome);
		
		// Verification
		Mockito.verify(homeIntegrationMapper, VerificationModeFactory.only()).map(any(HomeIntegrationModel.class));
	}
}
