package com.journal.HACKATHON25.extract;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journal.HACKATHON25.database.DatabaseConnection;

@RestController
@RequestMapping("/signup")
public class SignUpController {
        public String user; 
        public String pass;

    @PostMapping
    public void getSignUpCredentials(@RequestBody SignUp loginRequest) {
        System.out.println("Username: " + loginRequest.getUsername());
        System.out.println("Password: " + loginRequest.getPassword());
        System.out.println("Sign up successful");
        this.user = loginRequest.getUsername(); 
        this.pass = loginRequest.getPassword();
        try {
            DatabaseConnection db = new DatabaseConnection(user, this.pass);
            db.closeConnection();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
