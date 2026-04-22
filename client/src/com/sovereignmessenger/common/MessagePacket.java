package com.sovereignmessenger.common;

import javax.crypto.SealedObject;

public class MessagePacket extends NetworkPacket {
    private String sender;
    private String recipient;
    private SealedObject content;

    
    public MessagePacket() {

    }
    
}
