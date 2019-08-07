package com.adidas.email.controllers;



import com.fasterxml.jackson.databind.JsonNode;
import com.adidas.email.services.AsyncService;
import com.adidas.email.services.EmailServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/sendemail")
public class EmailController {


    @Autowired
    private AsyncService asyncService;
    /*@Autowired
    public EmailServiceImpl emailService;
*/
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> upperNewsletter(@RequestParam(value="email") String email) throws IOException {

        //emailService.sendSimpleMessage(email,"test", "hola loli");

        asyncService.process(email);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Request is under process");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
