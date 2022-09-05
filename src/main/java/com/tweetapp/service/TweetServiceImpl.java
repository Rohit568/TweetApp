package com.tweetapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoCommandException;
import com.tweetapp.dao.TweetEntity;
import com.tweetapp.dao.UserEntity;
import com.tweetapp.exception.CustomException;
import com.tweetapp.payloads.CommentPojo;
import com.tweetapp.payloads.EditPojo;
import com.tweetapp.payloads.ReplyPojo;
import com.tweetapp.payloads.TweetResponse;
import com.tweetapp.payloads.UserResponse;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.repository.UserRepository;

@Service
public class TweetServiceImpl implements TweetService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TweetRepository tweetRepository;
	
	public List<String> findallusers() {
		// TODO Auto-generated method stub
		try {
		List<UserEntity> users = userRepository.findAll();
		List<String> listUsers =new ArrayList<>();
		users.stream().forEach(user -> listUsers.add(user.getUsername()));
		return listUsers;
		}
		catch(MongoCommandException e) {
		
		}
	 
		return null;
		
	}

	@Override
	public void addnewtweet(TweetEntity tweet) throws Exception{
		// TODO Auto-generated method stub
		try {
		
		tweetRepository.save(tweet);
		}
		catch(MongoCommandException e) {
			
		}
		
		
	}

	@Override
	public List<TweetEntity> findalltweets() {
		// TODO Auto-generated method stub
		try {
		return tweetRepository.findAllByOrderByTweetDateDesc();
		}
		catch(MongoCommandException e) {
			return null;
		}
		
	}

	@Override
	public List<TweetEntity> getallthetweetsbyusername(String username) {
		// TODO Auto-generated method stub
		try {
		return tweetRepository.findTweetEntityByUsername(username);
		}
		catch(MongoCommandException e) {
			return null;
		}
		
	}
	@Override
	public TweetEntity likeupdate(String tweetId, String username) throws CustomException {
		// TODO Auto-generated method stub
		
	       try {
	    	TweetEntity tweet = tweetRepository.findById(tweetId).orElse(null);
	    	System.out.println(tweet.getTagText() + "**************************");
	    	
	    	Set<String> set = tweet.getLikes();
	    	
	    	set.add(username);
	    	tweet.setLikes(set);
	    	tweet.setLikesCount(set.size());
	    	tweetRepository.save(tweet);
	    	return tweet;
	       }
	       catch(Exception e)
	       {
	    	   return null;
	       }
	   
	
	}
	
	
	
	@Override
	public TweetEntity commentupdate(String tweetId, String username, ReplyPojo reply) {
		// TODO Auto-generated method stub
		try {
			TweetEntity tweet = tweetRepository.findById(tweetId).orElse(null);
			List<CommentPojo>  list = tweet.getComments();
			LocalDateTime dateTime = LocalDateTime.now();
	    	list.add(new CommentPojo(username, reply.getReply(),reply.getImageUrl(), dateTime));
	    	tweet.setCommentCount(list.size());
	    	tweet.setComments(list);
	    	tweetRepository.save(tweet);
	    	return tweet;
		    
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	@Override
	public TweetEntity edittweet(EditPojo tweet) {
		// TODO Auto-generated method stub
		try {
			TweetEntity t = tweetRepository.findByTweetId(tweet.getTweetId());
			t.setTweetText(tweet.getTweetText());
			t.setTagText(tweet.getTagText());
			tweetRepository.save(t);
			return t;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public void deletetweet(String username, String tweetId) {
		// TODO Auto-generated method stub
		
		try {
		     tweetRepository.deleteByTweetId(tweetId);
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}

	@Override
	public UserResponse findinfoofuser(String username) {
		// TODO Auto-generated method stub
		try {
			UserEntity u = userRepository.findUserEntityByUsername(username);
			if(u == null)
				return null;
			List<TweetEntity> tweetList = tweetRepository.findTweetEntityByUsername(username);
			if(tweetList == null)
				return null;
			
			List<TweetResponse> l = tweetList.stream()
					.map( t->  new TweetResponse(
					t.getId(),t.getTweetText(), t.getTagText(), t.getTweetDate(), t.getLikesCount(),
						t.getCommentCount(),t.getLikes(), t.getComments()))
					.collect(Collectors.toList());
					
					
			
			UserResponse ur = new UserResponse();
			ur.setFirstName(u.getFirstName());
			ur.setLastName(u.getLastName());
			ur.setContact(u.getContact());
			ur.setEmail(u.getEmail());
			ur.setTweetResponse(l);
			return ur;
			
			
		}
		catch(Exception e) {
			
		}
		return null;
	} 
}
