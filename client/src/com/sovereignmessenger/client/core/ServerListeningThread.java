package com.sovereignmessenger.client.core;

import java.io.IOException;
import java.io.ObjectInputStream;

import com.sovereignmessenger.common.*;


public class ServerListeningThread implements Runnable {
    private ObjectInputStream inputStream = null;
    private NetworkController networkController = null; 

    public ServerListeningThread(NetworkController networkController, ObjectInputStream inputStream) {
        this.networkController = networkController;
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        NetworkPacket packet = null;
        try {
            while ((packet = (NetworkPacket) inputStream.readObject()) != null) {
                if (packet instanceof MessagePacket) {
                    
                } else if (packet instanceof LoginResponse) {
                    networkController.handleLoginResponse((LoginResponse) packet);
                } else if (packet instanceof UserListPacket) { 
                    networkController.updateOnlineUsers((UserListPacket) packet);
                } else if (packet instanceof ErrorPacket) {

                }
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
