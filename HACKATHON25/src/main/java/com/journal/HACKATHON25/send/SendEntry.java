package com.journal.HACKATHON25.send;

public class SendEntry {
    private int entryId;
    private int userId;
    private String title;
    private String date;
    private String text;

    public SendEntry(int entryId, int userId, String title, String date, String text) {
        this.entryId = entryId;
        this.userId = userId;
        this.title = title;
        this.date = date;
        this.text = text;
    }

    public int getEntryId() {
        return entryId;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
    
}
