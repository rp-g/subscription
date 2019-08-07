package com.adidas.publicservice.domain;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class Subscriber {

    /**
     * the tags in this class validate the data
     */

    @NotEmpty(message = "Please provide email")
    @Email
    private String email;

    @Size(min=2, max=30)
    private String firstName;

    private Gender gender;

    @NotNull(message = "Please provide a date of birth") @Past
    private Date dateOfBith;

    @NotNull(message = "Please provide flag Newsletter")
    private Boolean flagNewsletter;

    @NotNull(message = "Please provide id Campaign")
    private Integer idCampaign;


    public enum Gender {
        MALE, FEMALE
    }
}
