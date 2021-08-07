package com.example.leraning.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailService {



    private final  JavaMailSender mailSender;
    @Value("${app.email.from}")
    private String from;

    public EmailService(JavaMailSender mailSender) {

        this.mailSender = mailSender;
    }

    public void send(String to,String subject,String html){
        MimeMessagePreparator message = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(from+"@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html,true);
        };
        mailSender.send(message);
    }
}
