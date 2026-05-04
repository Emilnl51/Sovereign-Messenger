package com.sovereignmessenger.client.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.sovereignmessenger.common.LoginPacket;
import com.sovereignmessenger.common.LoginResponse;
import com.sovereignmessenger.common.NetworkPacket;
import com.sovereignmessenger.common.UserListPacket;

public class NetworkController {
    private final String ipAddress = "127.0.0.1";
    private final int port = 2001;
    private ViewController viewController = null;

    private Socket socket = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;

    private ChatModel chatModel = null;
    
    public NetworkController(ChatModel model) {
        try {
            socket = new Socket(ipAddress, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  

        ServerListeningThread task = new ServerListeningThread(this, in);
        Thread liThread = new Thread(task);
        liThread.start();
        this.chatModel = model;
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }

    public void loginAttempt(String userName, String password) {
        //TODO: encrypt password


        LoginPacket packet = new LoginPacket(userName, password);
        sendMessage(packet);
    }

    public void updateOnlineUsers(UserListPacket onlineUsers) {
        viewController.updateOnlineUsers(onlineUsers.getOnlineUsers());
    }

    public void handleLoginResponse(LoginResponse response) {
        if (response.getSuccess()) {
            viewController.setChatView();
            chatModel.setLoggedInUser(response.getUserName());
        }
    }

    private void sendMessage(NetworkPacket packet) {
        try {
            out.writeObject(packet);
            out.flush();
            out.reset();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
