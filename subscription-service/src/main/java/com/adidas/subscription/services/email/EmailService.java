package com.adidas.subscription.services.email;


import com.adidas.subscription.services.bridge.EmailServiceBridge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class EmailService {

    private final static Logger LOGGER = LogManager.getLogger(EmailService.class);

    private final EmailServiceBridge emailServiceBridge;

    @Autowired
    public EmailService(EmailServiceBridge serviceBridge) {
        this.emailServiceBridge = serviceBridge;
    }


    @CachePut("configuration")
    public Optional<Object> sendEmail(String email){

        LOGGER.info("sendEmail async call");


        return  Optional.ofNullable(emailServiceBridge.postMicroservice(email));
    }

}
