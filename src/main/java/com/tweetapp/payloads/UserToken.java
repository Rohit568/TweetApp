package com.tweetapp.payloads;


public class UserToken {

	String username;
	String token;
	public UserToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserToken(String username, String token) {
		super();
		this.username = username;
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
