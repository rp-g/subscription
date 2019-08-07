package com.adidas.subscription.domain;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Past;
import java.util.Date;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/* The same class that exist in Public Service */
@Getter
@Setter
public class Subscriber {

    @NotEmpty
    @Email
    private String email;

    @Size(min=2, max=30)
    private String firstName;

    private Gender gender;

    @DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull @Past
    private Date dateOfBith;

    @NotNull
    private boolean flagNewsletter;

    @NotNull
    private int idCampaign;


    public enum Gender {
        MALE, FEMALE
    }
}
