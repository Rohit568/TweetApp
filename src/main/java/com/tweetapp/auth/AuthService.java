package com.tweetapp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.tweetapp.auth.service.JwtUtil;
import com.tweetapp.auth.service.UserService;
import com.tweetapp.dao.UserEntity;
import com.tweetapp.exception.CustomException;
import com.tweetapp.payloads.AuthResponse;
import com.tweetapp.payloads.LoginCredential;
import com.tweetapp.payloads.UserToken;
import com.tweetapp.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	UserService userService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	UserRepository userRepository;
	
	public UserToken login(LoginCredential loginCredential) throws CustomException
	{
		UserDetails user = userService.loadUserByUsername(loginCredential.getUsername());
		if(user.getPassword().equals(loginCredential.getPassword()))
		{
			String token = jwtUtil.generateToken(user);
			return new UserToken(user.getUsername(), token, true);
		}
		else
		{
			throw new CustomException("Username or password is not valid");
		}
	}
	
	public AuthResponse validate(String token) throws CustomException {
		String token1 = token.substring(7);
		if(jwtUtil.validateToken(token1))
			return new AuthResponse(jwtUtil.extractUsername(token1), true);
		else 
		    throw new CustomException("Token is not valid");
		
	}

	public boolean changePassword(String username, String password) {
		// TODO Auto-generated method stub
		try {
			UserEntity user= userRepository.findUserEntityByUsername(username);
			user.setPassword(password);
			userRepository.save(user);
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
