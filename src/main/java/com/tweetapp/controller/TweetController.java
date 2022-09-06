package com.tweetapp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.auth.AuthService;
import com.tweetapp.auth.service.JwtUtil;
import com.tweetapp.dao.TweetEntity;
import com.tweetapp.dao.UserEntity;
import com.tweetapp.exception.CustomException;
import com.tweetapp.payloads.ChangePassoword;
import com.tweetapp.payloads.CommentPojo;
import com.tweetapp.payloads.CustomResponse;
import com.tweetapp.payloads.EditPojo;
import com.tweetapp.payloads.IsAuthorized;
import com.tweetapp.payloads.LoginCredential;
import com.tweetapp.payloads.ReplyPojo;
import com.tweetapp.payloads.ResponseMessage;
import com.tweetapp.payloads.Tweet;
import com.tweetapp.payloads.UserResponse;
import com.tweetapp.payloads.UserToken;
import com.tweetapp.repository.UserRepository;
import com.tweetapp.service.TweetService;

import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1.0/tweets")
public class TweetController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthService authService;

	@Autowired
	TweetService tweetService;
	
	@Autowired
	JwtUtil jwtUtil;

	

	@PostMapping("/login")
	@ApiOperation(notes = "This is for login", value = "Enter the valid credential")
	public ResponseEntity<?> login(@RequestBody LoginCredential request) throws CustomException {
		try {
		UserToken userToken = authService.login(request);
		return new ResponseEntity<UserToken>(userToken, HttpStatus.OK);
		}
		catch(Exception e )
		{
			return new ResponseEntity<>(new ResponseMessage("either password or username is wrong"), HttpStatus.OK);
		}

	}
	@GetMapping("/authorize")
	@ApiOperation(notes = "This is for login", value = "Enter the valid credential")
	public ResponseEntity<?> authorize(@RequestHeader(value = "Authorization" , required = true) String token) throws CustomException {
		
		if(authService.validate(token).isValid()) {
			return new ResponseEntity<IsAuthorized>(new IsAuthorized(true), HttpStatus.OK);
			}
		return new ResponseEntity<IsAuthorized>(new IsAuthorized(false), HttpStatus.OK);

	}

	@PostMapping("/register")
	@ApiOperation(notes = "register", value = "Register a new user")
	public ResponseEntity<?> register(@RequestBody UserEntity user) {
		try {
			UserEntity u = userRepository.findUserEntityByUsername(user.getUsername());
			if (u != null) {
			
				return (ResponseEntity<?>) ResponseEntity.badRequest();
			}

			userRepository.save(user);
			ResponseMessage rm = new ResponseMessage(user.getFirstName() + " you uccessfully registered");
			return new ResponseEntity<ResponseMessage>(rm , HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("error"), HttpStatus.OK);
		}
	}
	@GetMapping("/all")
	@ApiOperation(notes = "tweets" ,value = "returns all tweets")
	public ResponseEntity<?> getAllTweets(@RequestHeader(value = "Authorization" , required = true) String token) throws CustomException
	{
       
		if(authService.validate(token).isValid()) {
		List<TweetEntity> tweets = tweetService.findalltweets();
		return new ResponseEntity<List<TweetEntity>>(tweets,HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Unautorized", HttpStatus.UNAUTHORIZED);
		

	 }
	@GetMapping("/user/search/{username}")
    @ApiOperation(notes = "users detail", value = "all information about user")
	public ResponseEntity<?> getuserdetailbyusername(@RequestHeader(value = "Authorization" , required = true) String token,
			@PathVariable("username") String username) throws CustomException
	{
		if(authService.validate(token).isValid()) {
			UserResponse ur = tweetService.findinfoofuser(username);
			return new ResponseEntity<UserResponse>(ur,HttpStatus.OK);
			}
			
			return new ResponseEntity<>("Unautorized", HttpStatus.UNAUTHORIZED);
	}

	
	@GetMapping("/username")
	@ApiOperation(notes = "users tweet", value = "returns the all user's tweet")
	public ResponseEntity<?> getalltweetsbyusername(@RequestHeader(value = "Authorization" , required = true) String token) throws CustomException
	{
       
		if(authService.validate(token).isValid()) {
		String username = jwtUtil.extractUsername(token.substring(7));
		List<TweetEntity> tweets = tweetService.getallthetweetsbyusername(username);
		return new ResponseEntity<List<TweetEntity>>(tweets,HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Unautorized", HttpStatus.UNAUTHORIZED);

	 }

	@GetMapping("/users/all")
	@ApiOperation(notes = "all tweet", value = "returns all Users")
	public ResponseEntity<?> getallusers(@RequestHeader(value = "Authorization" , required = true) String token) throws CustomException
	{
       
		if(authService.validate(token).isValid()) {
		List<String> users = tweetService.findallusers();
		return new ResponseEntity<List<String>>(users,HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Unautorized", HttpStatus.UNAUTHORIZED);

	 }
	
	@GetMapping("/like/{tweetId}")
	@ApiOperation(notes = "like", value = "responsible for who likes and likecount increase")
	public ResponseEntity<?> likeoparation(@RequestHeader(value = "Authorization" , required = true) String token, @PathVariable("tweetId") String tweetId) throws CustomException
	{
		if(authService.validate(token).isValid()) {
			String username  = jwtUtil.extractUsername(token.substring(7));
		    TweetEntity tweet = tweetService.likeupdate(tweetId,username);
		    return new ResponseEntity(tweet, HttpStatus.OK);
		
		}
		return new ResponseEntity<>("Unautorized", HttpStatus.UNAUTHORIZED);

	}
	@PutMapping("/reply/{tweetId}")
	@ApiOperation(notes = "This is for login", value = "responsible for tweet reply and tweet count")
	public ResponseEntity<?> replyoperation( @PathVariable("tweetId") String tweetId, @RequestBody ReplyPojo reply, @RequestHeader(value = "Authorization" , required = true) String token) throws CustomException
	{
		if(authService.validate(token).isValid()) {
			String username  = jwtUtil.extractUsername(token.substring(7));
		   TweetEntity tweet = tweetService.commentupdate(tweetId,username, reply);
		    return new ResponseEntity(tweet, HttpStatus.OK);
		
		}  
		return new ResponseEntity<>("Unautorized", HttpStatus.UNAUTHORIZED);

	}
	
	
	@PutMapping("/{username}/forgot")
	@ApiOperation(notes = "forgot", value = "change password")
	public ResponseEntity<?> changePassword(@PathVariable("username") String username,
			@RequestBody ChangePassoword newPassword,
			@RequestHeader(value = "Authorization" , required = true) String token ) throws CustomException
	{
		if(authService.validate(token).isValid())
		{
			ChangePassoword n = newPassword;
			String s = n.getPassword();
		    if(authService.changePassword(username, s)) {
		    	return new ResponseEntity<>(new ResponseMessage("success"), HttpStatus.OK);
		    }
		    else
		    	return new ResponseEntity<>(new ResponseMessage("error"), HttpStatus.OK);
		    	

		}
		return new ResponseEntity<>("Unautorized", HttpStatus.UNAUTHORIZED);


	}
	

	@PostMapping("/{username}/add")
	@ApiOperation(notes = "add", value = "adding a new tweet")
	public ResponseEntity<?> addnewtweet(@PathVariable("username") String username, @RequestBody Tweet twt,@RequestHeader(value = "Authorization" , required = true) String token ) throws Exception
	{
	  
		if(authService.validate(token).isValid())
		{
			TweetEntity tweet = new TweetEntity();
			tweet.setTweetText(twt.getTweettext());
			tweet.setTagText(twt.getTagtext());
			tweet.setLikesCount(0);
			tweet.setCommentCount(0);
			LocalDateTime dateTime = LocalDateTime.now();
			tweet.setTweetDate(dateTime);
			List<CommentPojo> comments= new ArrayList<CommentPojo>();
			tweet.setComments(comments);
			Set<String> likes = new HashSet<String>();
			tweet.setLikes(likes);
			tweet.setUsername(username);
			System.out.println(tweet.getTagText() + "*******************************" + twt.getTagtext());
			tweetService.addnewtweet(tweet);
			
			return new ResponseEntity<>(new ResponseMessage("success"), HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Unautorized", HttpStatus.UNAUTHORIZED);

		
	}
	@PutMapping("/{username}/update")
	@ApiOperation(notes = "update tweet", value = "updating a tweet")
	public ResponseEntity<?> edittweet(@PathVariable("username") String username, @RequestBody EditPojo tweet,@RequestHeader(value = "Authorization" , required = true) String token ) throws CustomException
	{
	  
		if(authService.validate(token).isValid())
		{
			tweetService.edittweet(tweet);
			return new ResponseEntity<>(new ResponseMessage("success"), HttpStatus.OK);
		}
		

		return new ResponseEntity<>("Unautorized", HttpStatus.UNAUTHORIZED);
		
	}
	@DeleteMapping("/{username}/delete/{tweetId}")
	@ApiOperation(notes = "delete", value = "deleting a tweet")
	public ResponseEntity<?> deletetweet(@PathVariable("username") String username, @PathVariable("tweetId") String tweetId,@RequestHeader(value = "Authorization" , required = true) String token ) throws CustomException
	{
		if(authService.validate(token).isValid())
		{
			tweetService.deletetweet(username, tweetId);
			return new ResponseEntity<>(new ResponseMessage("deleted"), HttpStatus.OK);
		}
		return new ResponseEntity<>("Unautorized", HttpStatus.UNAUTHORIZED);
		
	}
	
	
	
}

