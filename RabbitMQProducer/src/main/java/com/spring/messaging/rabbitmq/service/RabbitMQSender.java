package com.spring.messaging.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spring.messaging.rabbitmq.model.Employee;

@Service
public class RabbitMQSender {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Value("${spring.rabbitmq.template.exchange}")
	private String exchangeName;

	@Value("${spring.rabbitmq.template.routingKey}")
	private String routingKey;

	public void send(Employee employee) {
		rabbitTemplate.convertAndSend(exchangeName, routingKey, employee);
		System.out.println("Sending message from Producer: " + employee);

	}

}
