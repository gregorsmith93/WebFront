package com.webfront.integration.model;

public class HomeIntegrationModel {

	private String message;
	
	public HomeIntegrationModel(String message) {
		this.message = message;
	}
	
	public HomeIntegrationModel() {
		
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
