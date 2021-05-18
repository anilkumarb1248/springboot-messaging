package com.spring.messaging.rabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@SuppressWarnings("deprecation")
@Service
public class QueueReturnCallbackService implements RabbitTemplate.ReturnCallback{
	
	Logger LOGGER = LoggerFactory.getLogger(QueueReturnCallbackService.class);

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		LOGGER.info("ReturnCallbackService is called");
		LOGGER.info("Queue has received the message ===> replyCode={} ,replyText={} ,exchange={} ,routingKey={}", replyCode, replyText, exchange, routingKey);
		
	}

}
