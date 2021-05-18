package com.spring.messaging.rabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExchangeConfirmCallbackService implements RabbitTemplate.ConfirmCallback {

	Logger LOGGER = LoggerFactory.getLogger(ExchangeConfirmCallbackService.class);

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		LOGGER.info("ConfirmCallbackService is called");

		if (ack) {
			LOGGER.info("The Exchange has received the message, correlationData={} ,ack={}, cause={}", correlationData,
					ack, cause);
		} else {
			LOGGER.error("Error while sending to Exchange");
		}
	}
}
