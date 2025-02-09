package com.journal.HACKATHON25.send;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.journal.HACKATHON25.database.DatabaseConnection;
import com.journal.HACKATHON25.service.UserService;

@RestController
@RequestMapping("/entries")
@CrossOrigin(origins = "*")
public class SendEntryController {
    private final UserService userService;
    private String user;

    @GetMapping
    public ResponseEntity<List<SendEntry>> getAllEntries() {
        List<SendEntry> entries = null;
        this.user = userService.getCurrentUser();
        try {
            DatabaseConnection db = new DatabaseConnection(this.user);
            entries = db.getAllEntries();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return ResponseEntity.ok(entries);
    }

    public SendEntryController(UserService userService) {
        this.userService = userService;
    }
}
