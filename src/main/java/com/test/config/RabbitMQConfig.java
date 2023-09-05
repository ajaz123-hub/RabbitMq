package com.test.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.QueueBuilder;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue}")
    private String queueName;
    
    @Value("${rabbitmq.exchange}")
    private String exchangeName;
    
    @Value("${rabbitmq.routing-key}")
  private String routingKey;

    @Bean
	DirectExchange deadLetterExchange() {
		return new DirectExchange("deadLetterExchange");
	}
    
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }
//    @Bean
//    public Queue queue() {
//        return new Queue(queueName);
//    }

    @Bean
	Queue dlq() {
		return QueueBuilder.durable("deadLetter.queue").build();
	}

	@Bean
	Queue queue() {
		return QueueBuilder.durable(queueName).withArgument("x-dead-letter-exchange", "deadLetterExchange")
				.withArgument("x-dead-letter-routing-key", "deadLetter").build();
	}

	@Bean
	Binding DLQbinding() {
		return BindingBuilder.bind(dlq()).to(deadLetterExchange()).with("deadLetter");
	}

   

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
    @Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	
	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
   

	
	
}