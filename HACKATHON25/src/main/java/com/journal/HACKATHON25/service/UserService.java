package com.journal.HACKATHON25.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private String currentUser;

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String user) {
        this.currentUser = user;

    }
}
