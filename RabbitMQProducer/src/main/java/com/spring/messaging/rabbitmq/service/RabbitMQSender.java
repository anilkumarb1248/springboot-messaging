package com.spring.messaging.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.messaging.rabbitmq.model.Employee;

@Service
public class RabbitMQSender {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	private ExchangeConfirmCallbackService confirmCallbackService;

	@Autowired
	private QueueReturnCallbackService returnCallbackService;

	public void send(String exchangeName, String routingKey, Employee employee) {

		// Ensure that the message can be returned to the queue if it fails to send
		// Note: yml needs to configure publisher returns: true
		rabbitTemplate.setMandatory(true);
//		rabbitTemplate.containerAckMode(AcknowledgeMode.MANUAL);

		// After the consumer confirms that the message has been received, the manual
		// ack receipt callback is processed
		rabbitTemplate.setConfirmCallback(confirmCallbackService);

		// Message delivery to queue failed callback processing
		rabbitTemplate.setReturnCallback(returnCallbackService);

		rabbitTemplate.convertAndSend(exchangeName, routingKey, employee);
		
		System.out.println("Sending message from Producer: " + employee);

	}

}
