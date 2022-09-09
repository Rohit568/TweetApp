package com.tweetapp.payloads;

import java.util.List;

public class UserResponse {
	
    private String  username;
	private String  firstName;
	private String  lastName;
	private String  email;
	private String  contact;
	private List<TweetResponse> tweetResponse;
	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserResponse(String username, String firstName, String lastName, String email, 
			String contact, List<TweetResponse> tweetResponse) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	
		this.contact = contact;
		this.tweetResponse = tweetResponse;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public List<TweetResponse> getTweetResponse() {
		return tweetResponse;
	}
	public void setTweetResponse(List<TweetResponse> tweetResponse) {
		this.tweetResponse = tweetResponse;
	}
	
	
	
	
}
