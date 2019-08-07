package com.adidas.subscription.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter

// Entity for mysql
@Entity
@Table(name = "subscription")
public class Subscription {

    // Persistent Fields:

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idsubscription;

    private String email;

    //@Column(name = "firstName")
    private String firstName;

    private String gender;


    private Date dateOfBirth;


    private boolean flagNewsletter;


    private int idCampaign;



}
