package com.webfront.controller.model;

public class HomeControllerModel {

	private String message;
	
	private int requestCount;
	
	public HomeControllerModel(String message) {
		this.message = message;
	}
	
	public HomeControllerModel(int requestCount) {
		this.requestCount = requestCount;
	}
	
	public HomeControllerModel() {
		
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}
	
	public int getRequestCount() {
		return this.requestCount;
	}
}
