package com.adidas.publicservice.services.bridge;

import com.adidas.publicservice.domain.Subscriber;
import com.adidas.publicservice.properties.SubscriptionServiceProperties;
import com.google.common.collect.Maps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

//Connection to subscription service
@Component
public class SubscriptionServiceBridge implements ServiceBridge {

    private final static Logger LOGGER = LogManager.getLogger(ServiceBridge.class);

    private static final String SUBSCRIPTION_PATH = "/subscription";

    private final SubscriptionServiceProperties properties;

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    public SubscriptionServiceBridge(SubscriptionServiceProperties properties) {
        this.properties = properties;
    }

    @Override
    public Object postMicroservice(Subscriber subscriber) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity(subscriber,headers);

        String url = createBuilder().toUriString();

        //Post to microservice
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);

        if(response == null || !response.getStatusCode().is2xxSuccessful()) {
            LOGGER.error("calls to subscription service failed, responde code: " + response.getStatusCode());

            return null;
            //throw new BridgeCallException("calls to subscription service failed, responde code: " + response.getStatusCode());
        }

        return response.getBody();
    }



    private UriComponentsBuilder createBuilder() {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        builder = builder.scheme("http").host(properties.getSubscriptionHost()).port(properties.getSubscriptionPort()).path(SUBSCRIPTION_PATH);
        return builder;
    }


}

