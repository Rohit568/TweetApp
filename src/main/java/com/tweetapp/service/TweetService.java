package com.tweetapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tweetapp.dao.TweetEntity;
import com.tweetapp.dao.UserEntity;
import com.tweetapp.exception.CustomException;
import com.tweetapp.payloads.EditPojo;
import com.tweetapp.payloads.ReplyPojo;
import com.tweetapp.payloads.UserResponse;
@Service
public interface TweetService {
	
	public List<String> findallusers();
	public List<TweetEntity> findalltweets();
	public void addnewtweet(TweetEntity tweet);
	public TweetEntity likeupdate(String tweetId, String username) throws CustomException;
	public TweetEntity commentupdate(String tweetId, String username, ReplyPojo reply);
	public List<TweetEntity> getallthetweetsbyusername(String username);
	public TweetEntity edittweet(EditPojo tweet);
	public void deletetweet(String username, String tweetId);
	public UserResponse findinfoofuser(String username);

}
