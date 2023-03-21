package com.example.simpleemailsender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleEmail(String toEmail,
                                String emailBody,
                                String emailSubject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("<YOUR EMAIL. What You Mentioned in application.properties File in Resources Folder.>");
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setText(emailBody);
        simpleMailMessage.setSubject(emailSubject);
        javaMailSender.send(simpleMailMessage);
        System.out.println("Mail Sent..... !!");

    }

    public void sendEmailwithAttachment(String toEmail,
                                        String emailBody,
                                        String emailSubject,
                                        String attachment) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("<YOUR EMAIL. What You Mentioned in application.properties File in Resources Folder.>");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setCc("<OTHERS EMAIL. Carbon Copy Email.>");
        mimeMessageHelper.setText(emailBody);
        mimeMessageHelper.setSubject(emailSubject);

        FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),
                fileSystemResource);
        javaMailSender.send(mimeMessage);
        System.out.println("Mail Sent..... !!");
        
    }
}
