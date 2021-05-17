package com.spring.messaging.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitMQConfig {

	@Value("${spring.rabbitmq.host}")
	String rabbitMQHost;

	@Value("${spring.rabbitmq.username}")
	String rabbitMQUsername;

	@Value("${spring.rabbitmq.password}")
	String rabbitMQPassword;

	@Value("${spring.rabbitmq.template.exchange}")
	private String exchangeName;

	@Value("${spring.rabbitmq.template.queue}")
	private String queueName;

	@Value("${spring.rabbitmq.template.routingKey}")
	private String routingKey;

	@Bean
	public Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(exchangeName);
	}

	@Bean
	public Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}
	
//	 @Bean
////	  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
////	      MessageListenerAdapter listenerAdapter) {
//	  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
//	    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//	    container.setConnectionFactory(connectionFactory);
//	    container.setQueueNames(queueName);
////	    container.setMessageListener(listenerAdapter);
//	    return container;
//	  }

//	  @Bean
//	  MessageListenerAdapter listenerAdapter(Receiver receiver) {
//	    return new MessageListenerAdapter(receiver, "receiveMessage");
//	  }

//	@Bean
//	CachingConnectionFactory connectionFactory() {
//		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(rabbitMQHost);
//		cachingConnectionFactory.setUsername(rabbitMQUsername);
//		cachingConnectionFactory.setPassword(rabbitMQPassword);
//		return cachingConnectionFactory;
//	}
//
//	@Bean
//	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//		rabbitTemplate.setMessageConverter(jsonMessageConverter());
//		return rabbitTemplate;
//	}
//
//	@Bean
//	public MessageConverter jsonMessageConverter() {
//		return new Jackson2JsonMessageConverter();
//	}

}
