package com.example.milehighotters;

import java.util.UUID;

public class UserItem {
    private UUID userID;
    private String username;
    private String password;
    private double due;
    private int sqlLogId;

    public UserItem() {
        userID = UUID.randomUUID();
    }

    public UserItem(UUID id) {
        userID = id;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getDue() {
        return due;
    }

    public void setDue(double due) {
        this.due = due;
    }

    public int getSqlLogId() {
        return sqlLogId;
    }

    public void setSqlLogId(int sqlLogId) {
        this.sqlLogId = sqlLogId;
    }

    @Override
    public String toString() {
        return username;
    }
}
