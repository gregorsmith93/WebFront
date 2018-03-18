package com.webfront.integration.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.io.IOException;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webfront.domain.Home;
import com.webfront.integration.ClientRequest;
import com.webfront.integration.HomeIntegrationHandler;
import com.webfront.integration.JsonIntegration;
import com.webfront.integration.mapper.HomeIntegrationMapper;
import com.webfront.integration.model.HomeIntegrationModel;

@RunWith(MockitoJUnitRunner.class)
public class HomeIntegrationTests {

	private static final String EXPECTED_MESSAGE = "";

	@InjectMocks
	private HomeIntegrationHandler homeIntegrationHandler;

	@Mock
	private HomeIntegrationMapper homeIntegrationMapper;

	@Mock
	private ClientRequest clientRequest;

	@Mock
	private JsonIntegration jsonIntegration;

	@Spy
	private Response mockResponse;

	@Test
	public void getHomeTest() throws IOException {

		// Setup
		final Home expectedHome = new Home();
		expectedHome.setMessage(EXPECTED_MESSAGE);

		final HomeIntegrationModel homeIntegrationModel = new HomeIntegrationModel(
				EXPECTED_MESSAGE);

		// Mocks
		Mockito.when(homeIntegrationMapper.map(any(HomeIntegrationModel.class)))
				.thenReturn(expectedHome);
		Mockito.when(jsonIntegration.getObjectFromJsonString(any(String.class),
				any())).thenReturn(homeIntegrationModel);
		Mockito.when(clientRequest.performClientRequest(any(String.class),
				any(RequestMethod.class), any(), any()))
				.thenReturn(mockResponse);
		Mockito.doReturn(200).when(mockResponse).getStatus();
		Mockito.doReturn(EXPECTED_MESSAGE).when(mockResponse)
				.readEntity(String.class);

		// Test
		final Home actualHome = homeIntegrationHandler.getHome();

		// Assertion
		assertEquals(expectedHome, actualHome);

		// Verification
		Mockito.verify(homeIntegrationMapper, VerificationModeFactory.only())
				.map(any(HomeIntegrationModel.class));
		Mockito.verify(jsonIntegration, VerificationModeFactory.only())
				.getObjectFromJsonString(any(String.class), any());
		Mockito.verify(clientRequest, VerificationModeFactory.only())
				.performClientRequest(any(String.class),
						any(RequestMethod.class), any(), any());

	}
}
