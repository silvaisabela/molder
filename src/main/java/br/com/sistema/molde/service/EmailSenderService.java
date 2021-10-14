package br.com.sistema.molde.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService{
	 @Autowired private JavaMailSender emailSender;

	    public void sendSimpleMessage(
	      String to, String subject, String text) {
	        SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("appmolde@gmail.com");
	        message.setTo(to); 
	        message.setSubject(subject); 
	        message.setText(text);
	        emailSender.send(message);
	    }
}
