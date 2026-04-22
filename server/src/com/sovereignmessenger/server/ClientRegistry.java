package com.sovereignmessenger.server;

import java.util.ArrayList;

import com.sovereignmessenger.common.User;

public class ClientRegistry {
    private ArrayList<User> loggedInUsers = null;

    public ClientRegistry() {
        loggedInUsers = new ArrayList<User>();
    }

    public void addOnlineUser(User user) {
        user.setOnline(true);
        loggedInUsers.add(user);
    }

    public void setUserOffline(User user) {
        user.setOnline(false);
        loggedInUsers.remove(user);
    }

    public ArrayList<User> getLoggedInUsers() {
        return loggedInUsers;
    }

}
