package com.test.service;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.test.entity.Book;

@Service
public class BookProducer {
	
	
	 @Autowired
	 private RabbitTemplate rabbitTemplate;


    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    public void sendCreateBookMessage(Book book) {
    	System.out.println("message send from producer");
        rabbitTemplate.convertAndSend(exchangeName, routingKey, book);
    }

    
    
}
