package com.adidas.publicservice;

import com.adidas.publicservice.domain.Subscriber;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.JsonPathAssertions;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorTests {

    Validator validator;

    @Before
    public void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Test
    public void checkMandatoryFieldsSubscriber() throws ParseException {
        Subscriber subscriber = new Subscriber();

        subscriber.setEmail("j@mail.com");

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String dateBirth = "2013-04-02";
        Date date = dateformat.parse(dateBirth);
        subscriber.setDateOfBith(date);

        subscriber.setFlagNewsletter(true);

        subscriber.setIdCampaign(2378);

        Set<ConstraintViolation<Subscriber>> violations = validator.validate(subscriber);

        assertEquals(violations.size(),0);
    }

    @Test
    public void checkMandatoryEmailSubscriber() throws ParseException {
        Subscriber subscriber = new Subscriber();

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String dateBirth = "2013-04-02";
        Date date = dateformat.parse(dateBirth);
        subscriber.setDateOfBith(date);

        subscriber.setFlagNewsletter(true);

        subscriber.setIdCampaign(2378);

        Set<ConstraintViolation<Subscriber>> violations = validator.validate(subscriber);

        assertEquals(violations.size(),1);
    }

    @Test
    public void checkMandatoryBirthSubscriber() throws ParseException {
        Subscriber subscriber = new Subscriber();

        subscriber.setEmail("j@mail.com");

        subscriber.setFlagNewsletter(true);

        subscriber.setIdCampaign(2378);

        Set<ConstraintViolation<Subscriber>> violations = validator.validate(subscriber);

        assertEquals(violations.size(),1);
    }

    @Test
    public void checkMandatoryFlagNewsletterSubscriber() throws ParseException {
        Subscriber subscriber = new Subscriber();

        subscriber.setEmail("j@mail.com");

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String dateBirth = "2013-04-02";
        Date date = dateformat.parse(dateBirth);
        subscriber.setDateOfBith(date);

        subscriber.setIdCampaign(2378);

        Set<ConstraintViolation<Subscriber>> violations = validator.validate(subscriber);

        assertEquals(violations.size(),1);
    }

    @Test
    public void checkMandatoryIdCampaignSubscriber() throws ParseException {
        Subscriber subscriber = new Subscriber();

        subscriber.setEmail("j@mail.com");

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String dateBirth = "2013-04-02";
        Date date = dateformat.parse(dateBirth);
        subscriber.setDateOfBith(date);

        subscriber.setFlagNewsletter(true);

        Set<ConstraintViolation<Subscriber>> violations = validator.validate(subscriber);

        assertEquals(violations.size(),1);
    }





}

