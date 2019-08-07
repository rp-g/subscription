package com.adidas.publicservice.services.bridge;

import com.adidas.publicservice.domain.Subscriber;

/* Connection between microservices */
public interface ServiceBridge {

    Object postMicroservice(Subscriber subscriber);
}
