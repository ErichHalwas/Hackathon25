package com.journal.HACKATHON25.extract;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignUpController {
   // private static final String SECRET_KEY = "mySecretKey";

    @PostMapping
    public void getSignUpCredentials(@RequestBody LoginRequest loginRequest) {
        System.out.println("Username: " + loginRequest.getUsername());
        System.out.println("Password: " + loginRequest.getPassword());
    }
    

}
