package com.sovereignmessenger.common;

import java.util.ArrayList;

public class UserListPacket extends NetworkPacket {
    ArrayList<UserDTO> onlineUsersDTO = null;

    public UserListPacket(ArrayList<User> users) {
        onlineUsersDTO = new ArrayList<UserDTO>();
        for (User user : users) {
            // onlineUsersDTO.add(new UserDTO(user.getUserName(),
            // user.getPublicKey().getBytes()));
            onlineUsersDTO.add(new UserDTO(user.getUserName()));
        }
    }

    public ArrayList<UserDTO> getOnlineUsers() {
        return onlineUsersDTO;
    }
}