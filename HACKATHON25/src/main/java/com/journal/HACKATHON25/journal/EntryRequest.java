package com.journal.HACKATHON25.journal;

public class EntryRequest {
    private String title;
    private String content;

    public EntryRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }   
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}