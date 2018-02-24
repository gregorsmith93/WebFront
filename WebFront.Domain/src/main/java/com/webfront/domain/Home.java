package com.webfront.domain;

public class Home {

	private String message;
	
	public Home(String message) {
		this.message = message;
	}
	
	public Home() {
		
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
