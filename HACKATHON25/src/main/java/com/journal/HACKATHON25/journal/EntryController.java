package com.journal.HACKATHON25.journal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saveEntry")
public class EntryController {
   // private static final String SECRET_KEY = "mySecretKey";

    @PostMapping
    public void saveEntry(@RequestBody EntryRequest entryRequest) {
        System.out.println("Title: " + entryRequest.getTitle());
        System.out.println("Content: " + entryRequest.getContent());
        System.out.println("Entry successful");
        //DatabaseConnection db = new DatabaseConnection();
        //db.createDatabase();
        //db.createTable();
        //db.insertData(loginRequest);
    }
    

}
