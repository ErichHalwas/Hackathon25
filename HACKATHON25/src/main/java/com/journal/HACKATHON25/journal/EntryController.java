package com.journal.HACKATHON25.journal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saveEntry")
public class EntryController {

    @PostMapping
    public void saveEntry(@RequestBody EntryRequest entryRequest) {
        System.out.println("Title: " + entryRequest.getTitle());
        System.out.println("Text: " + entryRequest.getText());
        System.out.println("Entry successful");
    }
    

}
