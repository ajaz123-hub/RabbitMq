package com.test.service;


import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import com.test.entity.Book;
import com.test.repo.BookRepository;
//import org.springframework.retry.annotation.Backoff;
//import org.springframework.retry.annotation.Recover;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class BookConsumer {

    @Autowired
    private BookRepository bookRepository;
    
 @RabbitListener(queues = "${rabbitmq.queue}")
    
    public void getbooks(Book book) throws Exception  {
    	
    	System.out.println("hello word get");
        Optional<Book> findById = bookRepository.findById((long) 9);
         Book book2 = findById.get();
         System.out.println(book2);
         System.out.println("Book created222: " + book2.getTitle());
    	
    	
    }

//    @RabbitListener(queues = "${rabbitmq.queue}")
//    public void receiveCreateBookMessage(Book book) {
//    	
//    	System.out.println("hello wordl");
//        bookRepository.save(book);
////      
//        
//        
//        System.out.println("Book created: " + book.getTitle());
//    }
    
/*
    
    @RabbitListener(queues = "${rabbitmq.queue}")
    @Retryable(
            retryFor = {Exception.class},
            maxAttempts = 6,
            backoff = @Backoff(delay = 5000))
    public void getbooks(Book book) throws Exception {
    	try
    	{
    	System.out.println("hello word get");
        Optional<Book> findById = bookRepository.findById((long) 9);
         Book book2 = findById.get();
         System.out.println(book2);
         System.out.println("Book created222: " + book2.getTitle());
    	}
    	catch(Exception exception)
    	{
    		throw exception;
    	}
    }
////         
//        
//    }
//    
    @Recover
    public void recovergetbooks(Exception e) {
        // Log the error
        System.out.println("Error fetching book details: " + e.getMessage());

        // Return a default movie
        
    }*/
    
    /*@RabbitListener(queues = "${rabbitmq.queue}")
    @CircuitBreaker(name = "detailsgetbooks",fallbackMethod="getbooksFallBack")
    public void getbooks(Book book) throws Exception  {
    	
    	System.out.println("hello word get");
        Optional<Book> findById = bookRepository.findById((long) 2);
         Book book2 = findById.get();
         System.out.println(book2);
         System.out.println("Book created222: " + book2.getTitle());
    	
    	
    }
    private void getbooksFallBack(Book book, Exception e) {
		
    	   System.out.println("Error fetching book details: " + e.getMessage());

	}*/
    
   
    
}
    
    
