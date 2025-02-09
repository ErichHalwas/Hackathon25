package com.journal.HACKATHON25.journal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journal.HACKATHON25.database.DatabaseConnection;

@RestController
@RequestMapping("/createEntry")
public class EntryController {
   // private static final String SECRET_KEY = "mySecretKey";
    private final String user = " ";
    private final String title = " ";
    private String text;

    @PostMapping
    public void saveEntry(@RequestBody EntryRequest entryRequest) {
        System.out.println("Text: " + entryRequest.getText());
        System.out.println("Entry successful");
        this.text = entryRequest.getText();
        DatabaseConnection db = new DatabaseConnection(user, this.title, this.text);
        db.closeConnection();
        //db.createTable();
        //db.insertData(loginRequest);
    }
    

}
