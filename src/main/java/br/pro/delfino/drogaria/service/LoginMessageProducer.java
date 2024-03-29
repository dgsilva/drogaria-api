package br.pro.delfino.drogaria.service;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginMessageProducer {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	Queue queue;
	
	
	public void  sendMessage(String message) {
		rabbitTemplate.convertAndSend(queue.getName(), message);
	}

}
