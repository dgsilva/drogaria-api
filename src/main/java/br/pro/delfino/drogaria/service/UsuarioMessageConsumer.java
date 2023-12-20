package br.pro.delfino.drogaria.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.pro.delfino.drogaria.components.MailSenderComponent;
import br.pro.delfino.drogaria.dto.request.UsuarioMessageDTO;

@Service
public class UsuarioMessageConsumer {

	@Autowired
	MailSenderComponent mailSenderComponent;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@RabbitListener(queues = { "${queue.name}" })
	public void receive(@Payload String message) {
		try {
			
			//deserializar a mensagem gravada na fila
			UsuarioMessageDTO dto = objectMapper.readValue(message, UsuarioMessageDTO.class);
			
			//enviar por email
			mailSenderComponent.sendMessage(dto.getEmailTo(), dto.getSubject(), dto.getBody());			
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}	
}
