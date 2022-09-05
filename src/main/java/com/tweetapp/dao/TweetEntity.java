package com.tweetapp.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tweetapp.payloads.CommentPojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "tweets")
public class TweetEntity {
	
	@Id
	private String tweetId;
	private String username;
	private String tweetText;
	private String tagText;
	private LocalDateTime tweetDate;
	private Integer likesCount;
	private Integer commentCount;
	private Set<String> likes = new HashSet<String>();
	private List<CommentPojo> comments  = new ArrayList<CommentPojo>();
	public TweetEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TweetEntity(String id, String username, String tweetText, String tagText, LocalDateTime tweetDate,
			Integer likesCount, Integer commentCount, Set<String> likes, List<CommentPojo> comments) {
		super();
		this.tweetId = id;
		this.username = username;
		this.tweetText = tweetText;
		this.tagText = tagText;
		this.tweetDate = tweetDate;
		this.likesCount = likesCount;
		this.commentCount = commentCount;
		this.likes = likes;
		this.comments = comments;
	}
	public String getId() {
		return tweetId;
	}
	public void setId(String id) {
		this.tweetId = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTweetText() {
		return tweetText;
	}
	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}
	public String getTagText() {
		return tagText;
	}
	public void setTagText(String tagText) {
		this.tagText = tagText;
	}
	public LocalDateTime getTweetDate() {
		return tweetDate;
	}
	public void setTweetDate(LocalDateTime tweetDate) {
		this.tweetDate = tweetDate;
	}
	public Integer getLikesCount() {
		return likesCount;
	}
	public void setLikesCount(Integer likesCount) {
		this.likesCount = likesCount;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public Set<String> getLikes() {
		return likes;
	}
	public void setLikes(Set<String> likes) {
		this.likes = likes;
	}
	public List<CommentPojo> getComments() {
		return comments;
	}
	public void setComments(List<CommentPojo> comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "TweetEntity [tweetId=" + tweetId + ", username=" + username + ", tweetText=" + tweetText + ", tagText="
				+ tagText + ", tweetDate=" + tweetDate + ", likesCount=" + likesCount + ", commentCount=" + commentCount
				+ ", likes=" + likes + ", comments=" + comments + "]";
	}
	
	

}
