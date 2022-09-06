package com.tweetapp.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tweetapp.dao.TweetEntity;
@Repository
public interface TweetRepository extends MongoRepository<TweetEntity, String>{
	
	//List<TweetEntity> findAllByOrderByUpdatedAtDesc();

	List<TweetEntity> findAllByOrderByTweetDateDesc();
	@Query("{tweetId :?0}")
	TweetEntity findByTweetId(String tweetId);
	
	@Query("{username :?0}")
	List<TweetEntity>findTweetEntityByUsername(String username);
	@Query(value = "{tweetId :?0}", delete = true)
	void deleteByTweetId(String tweetId);
	
	
    Set<TweetEntity> findByTagTextLike(String tag);
	
  
}
