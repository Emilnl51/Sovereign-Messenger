package com.sovereignmessenger.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) {
        int port = 2001;

        DatabaseConnector dbConnection = new DatabaseConnector();
        DatabaseManager databaseManager = new DatabaseManager(dbConnection.getConnection());
        AccountManager accountManager = new AccountManager(databaseManager);
        ClientRegistry registry = new ClientRegistry();

        try (ServerSocket serverSocket = new ServerSocket(2001)) {
            Socket clientSocket = null;
            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client Connected from:" + clientSocket.getInetAddress().getHostAddress());
                ClientHandler clientHandler = new ClientHandler(clientSocket, accountManager, registry);
                clientHandler.start();
            }
        } catch (IOException e) {
            System.err.println("Server could not be started correctly:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
