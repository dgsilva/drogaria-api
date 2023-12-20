package br.pro.delfino.drogaria.components;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSenderComponent {

	@Autowired
	JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	String userName;
	
	/*
	 * Método para realizar o envio das mensagens
	 */
	public void sendMessage(String to, String subject, String body) throws Exception {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom(userName);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body, true);
		
		javaMailSender.send(message);
	}
}
