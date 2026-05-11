package com.sovereignmessenger.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.sovereignmessenger.common.LoginPacket;
import com.sovereignmessenger.common.LoginResponse;
import com.sovereignmessenger.common.LogoutPacket;
import com.sovereignmessenger.common.MessagePacket;
import com.sovereignmessenger.common.NetworkPacket;
import com.sovereignmessenger.common.User;
import com.sovereignmessenger.common.UserListPacket;

public class ClientHandler extends Thread{
    private Socket socket = null;

    private AccountManager accountManager = null;
    private ClientRegistry registry = null;
    private ServerController controller = null;

    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;

    private User user = null;
    public ClientHandler(Socket socket, AccountManager accountManager, ClientRegistry registry, ServerController controller) {
        this.socket = socket;
        this.accountManager = accountManager;
        this.registry = registry;
        this.controller = controller;

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUser() {
        return user;
    }

    @Override
    public void run() {
        try {
            NetworkPacket packet;
            System.out.println("server is reading from client");
            while ((packet = (NetworkPacket) in.readObject()) != null) {
                try {
                    handleIncomingPacket(packet);
                } catch (Exception e) {
                    System.err.println("Error handling packet: " + e.getMessage());
                    //TODO: SEND ERROR RESPONSE
                }
            }
        } catch (EOFException e) {
            System.out.println("SERVER ERROR: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("SERVER ERROR: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("SERVER ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            userLoggedOut();
            closeConnection();
        }
    }

    private void handleIncomingPacket(NetworkPacket packet) {
        System.out.println("message recieved");
        if (packet instanceof LoginPacket) {
            loginAttempt((LoginPacket) packet);
            System.out.println("login attempted");
        } else if (packet instanceof MessagePacket) {

        } else if (packet instanceof LogoutPacket) {
            userLoggedOut();
        }
    }

    private void loginAttempt(LoginPacket packet) {
        if (accountManager.handleLoginRequest(packet)) {
            System.out.println("User: " + packet.getUserName() + " logged in");
            user = new User(packet.getUserName());
            registry.setClientOnline(this);
            LoginResponse response = new LoginResponse(true, user.getUserName());
            sendPacket(response);
            // Broadcasting to all online clients that another is online
            controller.broadcastOnlineClients();
        }
    }

    public void sendOnlineClients(UserListPacket packet) {
        sendPacket(packet);
    } 

    private void sendPacket(NetworkPacket packet) {
        try {
            out.writeObject(packet);
            out.flush();
        } catch (IOException e) {
            System.out.println("Error sending to client");
            e.printStackTrace();
        }
    }

    private void userLoggedOut() {
        System.out.println("Client disconnected " + socket.getInetAddress() + " " + user.getUserName());
        registry.setClientOffline(this);
        this.closeConnection();
    }

    private void closeConnection() {
        try {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            System.err.println("Error closing client connection: " + e.getMessage());
        }
    }

}
