package com.spring.messaging.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.messaging.rabbitmq.model.Employee;
import com.spring.messaging.rabbitmq.service.RabbitMQSender;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {

	private static int counter = 1;

	@Autowired
	RabbitMQSender rabbitMQSender;

	@Value("${spring.rabbitmq.employee.exchange}")
	private String exchangeName;

	@Value("${spring.rabbitmq.employee.routingKey}")
	private String routingKey;

	@GetMapping("/send")
	public String sendToRabbitQueue() {

		Employee employee = new Employee(counter, "Name " + counter, 1000 * counter);
		rabbitMQSender.send(exchangeName, routingKey, employee);
		counter++;
		return "Message sent to RabbitMQ queue: " + employee;
	}

}
