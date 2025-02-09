package com.journal.HACKATHON25.extract;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public String handleLogin(@RequestBody SignUp loginRequest) {
        String username = loginRequest.getUsername();  
        String password = loginRequest.getPassword();  
        
        Login login = new Login();
        boolean isAuthenticated = login.authenticateUser(username, password);
        if (isAuthenticated) {
            return "Login successful";
        } else {
            return "Invalid username or password";
        }
    }
}
