package com.tweetapp.kafkaservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dao.TweetEntity;
import com.tweetapp.payloads.TweetResponse;
import com.tweetapp.service.TweetService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/kafka")
public final class KafkaController {
	@Autowired
    ProducerService producerService;
	@Autowired
	TweetService service;

    public KafkaController(ProducerService producerService) {
        this.producerService = producerService;
    }
    @ApiOperation(notes = "all tweets", value = "get alll tweets")
    @GetMapping(value = "/tweets")
    public ResponseEntity<?> sendMessageToKafkaTopic() {
    	List<TweetEntity> list = service.findalltweets();
    	List<TweetResponse> list2 = new ArrayList<>();
    	for(TweetEntity tweet : list) 
    		list2.add(new TweetResponse(tweet.getId(),tweet.getTweetText(), tweet.getTagText(), tweet.getTweetDate(), 
    				tweet.getLikesCount(), tweet.getCommentCount(), tweet.getLikes(), tweet.getComments()));
    	
        
        producerService.sendMessage(list2);
        return ResponseEntity.ok("Succefully produced");
        
    }
}

