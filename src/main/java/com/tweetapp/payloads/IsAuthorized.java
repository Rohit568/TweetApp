package com.tweetapp.payloads;

public class IsAuthorized {
	
	private boolean isAuth;

	public IsAuthorized(boolean isAuth) {
		
		this.isAuth = isAuth;
	}

	public IsAuthorized() {
		
		// TODO Auto-generated constructor stub
	}

	public boolean isAuth() {
		return isAuth;
	}

	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}
	

}
