package com.adidas.subscription.controllers;


import com.adidas.subscription.entities.Subscription;
import com.adidas.subscription.domain.Subscriber;
import com.adidas.subscription.repositories.SubscriptionDAO;
import com.adidas.subscription.services.email.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * Subscription service
 * In this microservice the data(subscription) is persisted in mysql and
 * an asynchronous call to the microservice "Email Service"
 */
@RestController
@RequestMapping(value = "/subscription")
public class SubscriptionController {

    private final static Logger LOGGER = LogManager.getLogger(SubscriptionController.class);

    @Autowired
    private SubscriptionDAO subscriptionDAO;

    private final EmailService emailService;

    @Autowired
    public SubscriptionController(EmailService emailService) {
        this.emailService = emailService;
    }



    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> receiveAlarms(@RequestBody Subscriber subscriber){
        try {


            Subscription subscription = subscriberToSubscription(subscriber);
            subscriptionDAO.save(subscription);


            emailService.sendEmail(subscriber.getEmail());


            return new ResponseEntity<>(subscription.getIdsubscription(), HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error("SubscriptionController , error code: " + e.getMessage());
            return null;
        }
    }

    private Subscription subscriberToSubscription(Subscriber subscriber){
        Subscription subscription= new Subscription();

        subscription.setEmail(subscriber.getEmail());
        subscription.setDateOfBirth(subscriber.getDateOfBith());
        subscription.setFlagNewsletter(subscriber.isFlagNewsletter());
        subscription.setIdCampaign(subscriber.getIdCampaign());

        if(subscriber.getGender() != null){
            subscription.setGender(subscriber.getGender().toString());
        }
        if(subscriber.getFirstName()!=null){
            subscription.setFirstName(subscriber.getFirstName());
        }

        return subscription;

    }
}
