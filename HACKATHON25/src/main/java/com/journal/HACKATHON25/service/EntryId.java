package com.journal.HACKATHON25.service;

import org.springframework.stereotype.Service;

@Service
public class EntryId {
    private int entryId;

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;

    }
}
