package com.adidas.email.services;

public interface EmailService {

    void sendSimpleMessage(String to,
                           String subject,
                           String text);
}
