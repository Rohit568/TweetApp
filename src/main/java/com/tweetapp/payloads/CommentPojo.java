package com.tweetapp.payloads;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class CommentPojo {
	
	private String username;
	private String comment;
	private String imageurl;
	private LocalDateTime commentDate;
	public CommentPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentPojo(String username, String comment, String imageurl, LocalDateTime commentDate) {
		super();
		this.username = username;
		this.comment = comment;
		this.imageurl = imageurl;
		this.commentDate = commentDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public LocalDateTime getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(LocalDateTime commentDate) {
		this.commentDate = commentDate;
	}
	
	
	

}
