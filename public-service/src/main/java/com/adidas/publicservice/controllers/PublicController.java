package com.adidas.publicservice.controllers;

import com.adidas.publicservice.services.subscription.SubscriptionService;
import com.adidas.publicservice.domain.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * the public service
 * this is the endpoint
 */

@RestController
@RequestMapping(value = "/subscription")
public class PublicController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public PublicController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }


    /**
     * Subscriptions contain: email, firstName, gender, dateOfBith, flag for consent and
     * the newsletter Id corresponding to the campaign.
     * Only gender and firstName are optional values.
     * @param
     * @return
     */
    //@RequestMapping(method = RequestMethod.POST)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> subscription(@Valid @RequestBody Subscriber subscriber) {

        //call the other microservice: Subscription Service
        Optional<Object> configurationOpt = subscriptionService.newSubscription(subscriber);

        //return
        if(configurationOpt.isPresent()){
            return new ResponseEntity<>("valid",HttpStatus.OK);
        }
        return new ResponseEntity<>("Subscription service error",HttpStatus.BAD_REQUEST);
    }


}
