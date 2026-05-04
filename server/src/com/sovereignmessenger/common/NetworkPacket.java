package com.sovereignmessenger.common;
import java.io.Serializable;

public class NetworkPacket implements Serializable {
    private String type;

    public NetworkPacket() {

    }

    public String getType() {
        return type;
    }
}
