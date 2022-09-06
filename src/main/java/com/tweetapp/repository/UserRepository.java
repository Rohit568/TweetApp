package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.dao.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String>{
	
	public UserEntity findUserEntityByUsername(String username);
	
	 public List<UserEntity> findByUsernameLike(String username);

}
