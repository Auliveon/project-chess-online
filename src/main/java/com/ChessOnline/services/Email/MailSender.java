package com.ChessOnline.services.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {
    @Autowired
    JavaMailSender mailSender;
    public void send(String sendTo, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(sendTo);
        simpleMailMessage.setSubject("Activation code");
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
    }


}

