package com.journal.HACKATHON25.journal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journal.HACKATHON25.database.DatabaseConnection;
import com.journal.HACKATHON25.service.UserService;

@RestController
@RequestMapping("/createEntry")
public class EntryController {
   // private static final String SECRET_KEY = "mySecretKey";
    private String user = " ";
    private final String title = " ";
    private String text;
    private final UserService userService;

    @PostMapping
    public void saveEntry(@RequestBody EntryRequest entryRequest) {
        System.out.println("Text: " + entryRequest.getText());
        System.out.println("Entry successful");
        this.user = userService.getCurrentUser();
        System.out.println(this.user);
        this.text = entryRequest.getText();
        try {
            DatabaseConnection db = new DatabaseConnection(this.user, this.title, this.text);
            db.closeConnection();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public EntryController(UserService userService) {
        this.userService = userService;
    }

}
