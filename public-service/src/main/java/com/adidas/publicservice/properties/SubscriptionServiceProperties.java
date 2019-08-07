package com.adidas.publicservice.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/* Properties for connection with Subscription Service (Microeservice) */
@Component
@ConfigurationProperties(prefix="micros")
@Getter
@Setter
public class SubscriptionServiceProperties {


    private String subscriptionHost;

    private int subscriptionPort;


}
