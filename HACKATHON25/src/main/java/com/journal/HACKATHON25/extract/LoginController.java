package com.journal.HACKATHON25.extract;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journal.HACKATHON25.database.DatabaseConnection;
import com.journal.HACKATHON25.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {
    private String username;
    private String password;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> handleLogin(@RequestBody Login login) {
        System.out.println("Username: " + login.getUsername());
        System.out.println("Password: " + login.getPassword());
        System.out.println("Sign up successful");
        userService.setCurrentUser(login.getUsername());
        this.username = login.getUsername();
        this.password = login.getPassword();
        try {
            DatabaseConnection db = new DatabaseConnection(username, password, false);
            db.closeConnection();
            if(db.authenticateUser()) {
                System.out.println("Logged In");
                return ResponseEntity.ok().body("Login Successful");
            } else {
                System.out.println("Invalid Username/Password");
                return ResponseEntity.ok().body("Invalid Username/Password");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Login Successful");
    }

    public LoginController(UserService userService) {
        this.userService = userService;       
    }
}
