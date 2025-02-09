package com.journal.HACKATHON25.journal;

public class EntryRequest {
private String text;

    public EntryRequest(String text) {
        this.text = text;
    }   

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}