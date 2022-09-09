package com.tweetapp.payloads;

public class Tweet {

	private String tweettext;
	private String tagtext;
	public Tweet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tweet(String tweettext, String tagtext) {
		super();
		this.tweettext = tweettext;
		this.tagtext = tagtext;
	}
	public String getTweettext() {
		return tweettext;
	}
	public void setTweettext(String tweettext) {
		this.tweettext = tweettext;
	}
	public String getTagtext() {
		return tagtext;
	}
	public void setTagtext(String tagtext) {
		this.tagtext = tagtext;
	}
	
}
