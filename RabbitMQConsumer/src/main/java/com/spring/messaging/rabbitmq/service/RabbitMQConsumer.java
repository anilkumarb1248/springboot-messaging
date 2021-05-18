package com.spring.messaging.rabbitmq.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.spring.messaging.rabbitmq.model.Employee;

@Service
public class RabbitMQConsumer {

	Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

	@Value("${spring.rabbitmq.employee.queue}")
	private String queueName;

//	public void recievedMessage(Employee employee, Channel channel) throws IOException {
	@RabbitListener(queues = "${spring.rabbitmq.employee.queue}")
	public void recievedMessage(Employee employee, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
			throws IOException {
		LOGGER.info("RabbitMQConsumer is called: {} ", employee);

//		channel.basicConsume(queueName, false, channel.getDefaultConsumer());

//		long deliveryTag = channel.basicGet(queueName, false).getEnvelope().getDeliveryTag();

		try {
			int a = 1 / 0;
			channel.basicAck(tag, false);
		} catch (Exception e) {
//			channel.basicReject(tag, true); // Entering into infinite loop
			channel.basicReject(tag, false);
		}

	}

//	public void recievedMessage(Employee employee) {
//	public void myAckListener(Employee employee, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
//			throws IOException {
//	@RabbitListener(queues = "${spring.rabbitmq.employee.queue}")
//	public void queueTestConsumer(Employee employee, Channel channel, Message message) {
//		LOGGER.info("RabbitMQConsumer is called");
//		try {
//			/**
//			 * Confirm the message without exception basicAck(long deliveryTag, boolean
//			 * multiple) deliveryTag: retrieve the index of the current message in the
//			 * queue; multiple: if it is true, it is batch confirmation, if the current
//			 * deliveryTag is 5, then it will be confirmed Messages whose deliveryTag is 5
//			 * and below; generally set to false
//			 */
////			channel.basicAck(tag, false);
//
//			// The current thread sleeps for 5 seconds, simulating the message consumption
//			// process
//			Thread.sleep(5000);
//			// Normal consumption news
//			LOGGER.info("Recieved Message From RabbitMQ: {}", employee);
//
//			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//			LOGGER.error("Message [{}] confirms successful processing", message);
//		} catch (Exception e) {
//
//			/**
//			 * No news will be received if there is an exception basicNack(long deliveryTag,
//			 * boolean multiple, boolean requeue) requeue: true means to return the message
//			 * to the current message queue, and it can also be resent to the consumer;
//			 * false: discard the message
//			 */
////			channel.basicNack(tag, false, true);
//
//			// redelivered = true, indicating that the message is processed repeatedly
//			Boolean redelivered = message.getMessageProperties().getRedelivered();
//
//			/**
//			 * Set the message re-entry queue here, for example, serialize and cache the
//			 * message to Redis, and record the number of re-entry queues If the message
//			 * reenters the queue once, such as 3 times, it will no longer reenter the queue
//			 * and will be rejected directly At this time, the message needs to be
//			 * compensated
//			 *
//			 * Channel.basicNack and channel.basicReject should be used in combination
//			 *
//			 */
//			try {
//
//				if (redelivered) {
//
//					/**
//					 * 1. Compensation mechanism for repeated processing of queue messages 2. Remove
//					 * the message from the queue to prevent the queue from blocking
//					 */
//					// Message has been processed repeatedly failed, throw away the message
//					channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // reject message
//					LOGGER.error("Message [{}] failed reprocessing, throw away the message", message);
//				}
//
//				// redelivered != true, indicating that the message was consumed for the first
//				// time
//				if (!redelivered) {
//
//					// The message is put back in the queue
//					channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//					LOGGER.error("Message [{}] failed to process, put it back in the queue", message);
//				}
//
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//
//			e.printStackTrace();
//		}
//	}
}