package com.sovereignmessenger.common;

public class LoginPacket extends NetworkPacket {
    private String userName;
    private String encryptPassword;

    public LoginPacket(String userName, String encryptedPassword) {
        this.userName = userName;
        this.encryptPassword = encryptedPassword; 
    }

    public String getUserName() {
        return userName;
    }

    public String getEncryptedPassword() {
        return encryptPassword;
    }
}
