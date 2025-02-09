package com.journal.HACKATHON25.save;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journal.HACKATHON25.database.DatabaseConnection;
import com.journal.HACKATHON25.service.UserService;

@RestController
@RequestMapping("/saveEntry")
public class SaveController {
    private String user;
    private String title;
    private String text;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> saveEntry(@RequestBody Save save) {
        System.out.println("Text: " + save.getText());
        System.out.println("Title: " + save.getTitle());
        System.out.println("Entry saved successful");
        this.user = userService.getCurrentUser();
        this.title = save.getTitle();
        System.out.println(this.user);
        this.text = save.getText();
        try {
            DatabaseConnection db = new DatabaseConnection(this.user, this.title, this.text, false);
            db.closeConnection();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Saved Entry Created");
    }
    
    public SaveController(UserService userService) {
        this.userService = userService;
    }
}
