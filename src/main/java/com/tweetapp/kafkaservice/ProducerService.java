package com.tweetapp.kafkaservice;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tweetapp.payloads.TweetResponse;

@Service
public final class ProducerService {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final String TOPIC = "kafkaTopic";

	public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(List<TweetResponse> tweets) {
		StringBuilder sb = new StringBuilder();
		for(TweetResponse tweet : tweets) 
			sb.append(tweet.toString()).append("/n");
		this.kafkaTemplate.send(TOPIC, sb.toString());
	}

	
}