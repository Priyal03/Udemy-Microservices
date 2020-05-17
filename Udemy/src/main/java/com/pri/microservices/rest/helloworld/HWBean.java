package com.pri.microservices.rest.helloworld;

public class HWBean {

	private String message;

	public HWBean(String string) {
		
		setMessage(string);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
