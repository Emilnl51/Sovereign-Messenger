package com.sovereignmessenger.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.sovereignmessenger.common.User;
import com.sovereignmessenger.common.UserListPacket;

public class ServerController {
    private DatabaseConnector dbConnection = null;
    private DatabaseManager databaseManager = null;
    private ClientRegistry registry = null;
    private AccountManager accountManager = null;

    public ServerController() {
        int port = 2001;

        dbConnection = new DatabaseConnector();
        databaseManager = new DatabaseManager(dbConnection.getConnection());
        registry = new ClientRegistry();
        accountManager = new AccountManager(databaseManager, registry);


        try (ServerSocket serverSocket = new ServerSocket(2001)) {
            Socket clientSocket = null;
            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client Connected from:" + clientSocket.getInetAddress().getHostAddress());
                ClientHandler clientHandler = new ClientHandler(clientSocket, accountManager, registry, this);
                clientHandler.start();
            }
        } catch (IOException e) {
            System.err.println("Server could not be started correctly:");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void broadcastOnlineClients() {
        ArrayList<ClientHandler> handlers = registry.getOnlineClients();

        ArrayList<User> userList = new ArrayList<User>();
        for (ClientHandler handler : handlers) {
            userList.add(handler.getUser());
        }

        UserListPacket userListPacket = new UserListPacket(userList);

        for (ClientHandler handler : handlers) {
            handler.sendOnlineClients(userListPacket);
        }
    }
}
