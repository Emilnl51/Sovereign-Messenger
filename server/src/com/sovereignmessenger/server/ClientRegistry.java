package com.sovereignmessenger.server;

import java.util.HashMap;

import com.sovereignmessenger.common.User;

public class ClientRegistry {
    private HashMap<String, User> loggedInUsers = null;

    public ClientRegistry() {
        loggedInUsers = new HashMap<String, User>();
    }

    public void addOnlineUser(User user) {
        user.setOnline(true);
        loggedInUsers.put(user.getUserName(), user);
    }

    public void setUserOffline(User user) {
        user.setOnline(false);
        loggedInUsers.remove(user.getUserName());
    }

    public HashMap<String, User> getLoggedInUsers() {
        return loggedInUsers;
    }
}
