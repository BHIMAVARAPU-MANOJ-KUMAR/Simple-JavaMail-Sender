package com.example.simpleemailsender;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SimpleemailsenderApplication {

	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(SimpleemailsenderApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		emailSenderService.sendEmailwithAttachment("<TO-EMAIL ADDRESS TO WHOM THE MAIL HAS TO BE SEND.>",
				"Hai. Hello.",
				"Simple Email Using Spring Java Mail Sender.",
				"<EMAIL ATTACHMENT FILE FULL PATH FROM YOUR LOCAL MACHINE. PLEASE INSERT HERE.>");
	}

}
