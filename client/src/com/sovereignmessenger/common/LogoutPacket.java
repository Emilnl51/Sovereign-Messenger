package com.sovereignmessenger.common;

public class LogoutPacket extends NetworkPacket {
    private String user;

    public LogoutPacket(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }
}
