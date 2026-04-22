package com.sovereignmessenger.common;

public class User {
    private String userName;
    private String publicKey;
    private String email;
    private boolean isOnline;
    // private String friends;

    // public User(String userName, String publicKey, String email, boolean isOnline) {

    // }

    public User(String userName) {

    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getUserName() {
        return userName;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getEmail() {
        return email;
    }

    public boolean getIsOnline() {
        return isOnline;
    }

}
