package com.sovereignmessenger.common;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private String userName;
    private byte[] publicKey;

    // public UserDTO(String name, byte[] publicKey) {
    // this.userName = name;
    // this.publicKey = publicKey;
    // }

    public UserDTO(String name) {
        this.userName = name;
        // this.publicKey = publicKey;
    }

    public String getUserName() {
        return userName;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }
}
