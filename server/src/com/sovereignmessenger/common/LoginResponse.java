package com.sovereignmessenger.common;

public class LoginResponse extends NetworkPacket {
    private boolean success = false;
    private String userName; 

    public LoginResponse(boolean success, String name) {
        this.success = success;
        this.userName = name;
    }
    
    public boolean getSuccess() {
        return success;
    }

    public String getUserName() {
        return userName;
    }
}
