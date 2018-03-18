package com.webfront.service.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnitRunner;

import com.webfront.domain.Home;
import com.webfront.integration.HomeIntegrationHandler;
import com.webfront.service.HomeServiceHandler;

@RunWith(MockitoJUnitRunner.class)
public class HomeServiceTests{

	private static final String EXPECTED_MESSAGE = "";
	
	@InjectMocks private HomeServiceHandler homeServiceHandler;
	
	@Mock private HomeIntegrationHandler homeIntegrationHandler;
	
	@Test
	public void getHomeTest() {
		
		// Setup
		Home expectedHome = new Home();
		expectedHome.setMessage(EXPECTED_MESSAGE);
		
		// Mocks
		Mockito.when(homeIntegrationHandler.getHome()).thenReturn(expectedHome);
		
		// Test
		Home actualHome = homeServiceHandler.getHome();
		
		// Assertion
		assertEquals(expectedHome, actualHome);
		
		// Verification
		Mockito.verify(homeIntegrationHandler, VerificationModeFactory.only()).getHome();
	}
}
