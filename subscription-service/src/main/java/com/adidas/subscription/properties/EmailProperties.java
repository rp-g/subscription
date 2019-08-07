package com.adidas.subscription.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



/* Properties for connection with Email Service (Microeservice) */
@Component
@ConfigurationProperties(prefix="micros")
@Getter
@Setter
public class EmailProperties {

    private String emailHost;

    private int emailPort;
}


