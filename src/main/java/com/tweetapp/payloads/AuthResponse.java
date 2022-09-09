package com.tweetapp.payloads;

import org.springframework.stereotype.Component;

@Component
public class AuthResponse {
	
	String username;
	boolean isValid;
	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AuthResponse(String username, boolean isValid) {
		super();
		this.username = username;
		this.isValid = isValid;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	

}
