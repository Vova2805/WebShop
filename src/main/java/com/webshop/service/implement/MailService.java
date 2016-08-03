package com.webshop.service.implement;

import com.webshop.service.interfaces.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService {
    @Autowired
    private MailSender mailSender;

    @Override
    public void sendEmail(String toAddress, String fromAddress, String subject, String msgBody) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        message.setTo(toAddress);
        message.setSubject(subject);
        message.setText(msgBody);

        mailSender.send(message);
    }
}
