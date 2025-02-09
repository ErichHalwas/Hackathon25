package com.journal.HACKATHON25.extract;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journal.HACKATHON25.database.DatabaseConnection;

@RestController
@RequestMapping("/signup")
public class SignUpController {
   // private static final String SECRET_KEY = "mySecretKey";
    //private SignUpRepository signUpRepository;

    @PostMapping
    
    public void getSignUpCredentials(@RequestBody SignUp loginRequest) {
        System.out.println("Username: " + loginRequest.getUsername());
        System.out.println("Password: " + loginRequest.getPassword());
        System.out.println("Sign up successful");
        String user = loginRequest.getUsername(); 
        String pass = loginRequest.getPassword();
        System.out.println(user);
        System.out.println(pass);
        try {
            DatabaseConnection db = new DatabaseConnection(user, pass);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
/** 
    public @ResponseBody String addNewUser(@RequestBody LoginRequest loginRequest) {
        signUpRepository.save(loginRequest);
        return "Saved";
    }
        */
/** 
    @GetMapping
    public @ResponseBody Iterable<LoginRequest> getAllUsers() {
    // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
*/
}
