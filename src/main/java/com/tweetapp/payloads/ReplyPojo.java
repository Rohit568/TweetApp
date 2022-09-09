package com.tweetapp.payloads;

public class ReplyPojo {

	private String reply;
	private String imageUrl;
	public ReplyPojo(String reply, String imageUrl) {
		super();
		this.reply = reply;
		this.imageUrl = imageUrl;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
