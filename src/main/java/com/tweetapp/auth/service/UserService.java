package com.tweetapp.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.UserEntity;
import com.tweetapp.repository.UserRepository;
@Service
public class UserService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity user = userRepository.findUserEntityByUsername(username);
		if(user== null)
			throw new UsernameNotFoundException("User not found");
		
		return new User(user.getUsername(), user.getPassword(), new ArrayList());
	}

}
