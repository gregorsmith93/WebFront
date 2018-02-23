package com.webfront.controller.model;

public class HomeControllerModel {

	private String message;
	
	public HomeControllerModel(String message) {
		this.message = message;
	}
	
	public HomeControllerModel() {
		
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
