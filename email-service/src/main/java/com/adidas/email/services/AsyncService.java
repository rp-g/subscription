package com.adidas.email.services;

import com.adidas.email.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncService.class);

    @Autowired
    public EmailServiceImpl emailService;

    @Async("asyncExecutor")
    public void process(String email) {
        logger.info("Received request to process in AsyncService.process()");

        emailService.sendSimpleMessage(email, Constants.SUBJECT, Constants.TEXT);

        logger.info("Processing complete");


    }
}
