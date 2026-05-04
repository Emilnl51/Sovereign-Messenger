package com.sovereignmessenger.client.core;

import java.util.ArrayList;

import com.sovereignmessenger.common.UserDTO;

public class ChatModel {
    private String userName;

    private ArrayList<UserDTO> onlineUsers = null;

    private UserDTO currentSelectedChat = null;

    public ChatModel() {

    }

    public void setLoggedInUser(String name) {
        this.userName = name;
    }

    public String getLoggedInUser() {
        return userName;
    }

    public ArrayList<UserDTO> getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(ArrayList<UserDTO> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    public UserDTO getCurrentSelectedChat() {
        return currentSelectedChat;
    }

    public void setCurrentSelectedChat(UserDTO currentSelectedChat) {
        this.currentSelectedChat = currentSelectedChat;
    }

    public UserDTO findUserByName(String name) {
        return onlineUsers.stream()
                .filter(u -> u.getUserName().equals(name))
                .findFirst()
                .orElse(null);
    } 
}
