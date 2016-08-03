package com.webshop.service.interfaces;

public interface IMailService {

    void sendEmail(String toAddress, String fromAddress, String subject, String msgBody);

}
