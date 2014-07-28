package com.cagnosolutions.cei.houseontherock.fantheflamedates.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service(value = "mailService")
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendSimpleEmail(String from, String subject, String body, String... to) {
		new Thread(
			() -> {
				SimpleMailMessage email = new SimpleMailMessage();
				email.setFrom(from);
				email.setReplyTo(from);
				email.setSubject(subject);
				email.setText(body);
				email.setTo(to);
				mailSender.send(email);
			}
		).start();
	}

}
