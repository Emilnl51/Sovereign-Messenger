package com.sovereignmessenger.common;

public class LoginResponse extends NetworkPacket {
    private boolean success = false;

    public LoginResponse(boolean success) {
        this.success = success;
    }
    
    public boolean getSuccess() {
        return success;
    }
}
