package com.adidas.subscription.services.bridge;


import com.adidas.subscription.properties.EmailProperties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


//Connection to email service
@Component
public class EmailServiceBridge implements ServiceBridge {

    private final static Logger LOGGER = LogManager.getLogger(ServiceBridge.class);

    private static final String SUBSCRIPTION_PATH = "/sendemail";

    private final EmailProperties properties;

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    public EmailServiceBridge(EmailProperties properties) {
        this.properties = properties;
    }

    @Override
    public Object postMicroservice(String email) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity(headers);

        String url = createBuilder().queryParam("email",email).toUriString();


        //Post to microservice
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
        if(response == null || !response.getStatusCode().is2xxSuccessful()) {
            LOGGER.error("calls to email service failed, responde code: " + response.getStatusCode());

            return null;
            //throw new BridgeCallException("calls to email service failed, responde code: " + response.getStatusCode());
        }

        return response.getBody();
    }


    private UriComponentsBuilder createBuilder() {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        builder = builder.scheme("http").host(properties.getEmailHost()).port(properties.getEmailPort()).path(SUBSCRIPTION_PATH);
        return builder;
    }


}

