package com.adidas.publicservice.services.subscription;


import com.adidas.publicservice.domain.Subscriber;
import com.adidas.publicservice.services.bridge.SubscriptionServiceBridge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class SubscriptionService {

    private final static Logger LOGGER = LogManager.getLogger(SubscriptionService.class);

    private final SubscriptionServiceBridge subscriptionServiceBridge;

    @Autowired
    public SubscriptionService(SubscriptionServiceBridge serviceBridge) {
        this.subscriptionServiceBridge = serviceBridge;
    }


    @CachePut("configuration")
    public Optional<Object> newSubscription(final Subscriber subscriber){

        LOGGER.info("New Subscription");


        return  Optional.ofNullable(subscriptionServiceBridge.postMicroservice(subscriber));
    }

}
