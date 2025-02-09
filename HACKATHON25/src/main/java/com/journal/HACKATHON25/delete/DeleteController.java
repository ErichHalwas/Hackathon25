package com.journal.HACKATHON25.delete;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journal.HACKATHON25.database.DatabaseConnection;
import com.journal.HACKATHON25.service.UserService;

@RestController
@RequestMapping("/entries")
public class DeleteController {
    private final UserService userService;
    private String user;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable int id) {
            this.user = userService.getCurrentUser();
            try {
            DatabaseConnection db = new DatabaseConnection(this.user, id);
            db.deleteEntries();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            return ResponseEntity.ok().body("Entry deleted)");
        }
    public DeleteController(UserService userService) {
        this.userService = userService;
    }
}