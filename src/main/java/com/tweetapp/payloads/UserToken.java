package com.tweetapp.payloads;


public class UserToken {

	String username;
	String token;
	boolean isLogin;
	public UserToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserToken(String username, String token, boolean isLogin) {
		super();
		this.username = username;
		this.token = token;
		this.isLogin = isLogin;
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
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	
	
}
