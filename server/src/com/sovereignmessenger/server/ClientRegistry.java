package com.sovereignmessenger.server;

import java.util.ArrayList;
import java.util.HashMap;

public class ClientRegistry {
    private HashMap<String, ClientHandler> onlineClients = null;

    public ClientRegistry() {
     onlineClients = new HashMap<String, ClientHandler>();
    }

    public void setClientOnline(ClientHandler client) {
        // user.setOnline(true);
     onlineClients.put(client.getUser().getUserName(), client);
    }

    public void setClientOffline(ClientHandler client) {
        // user.setOnline(false);
        onlineClients.remove(client.getUser().getUserName());
    }

    // public HashMap<String, ClientHandler> getOnlineClients() {
    //     return onlineClients;
    // }

    public ArrayList<ClientHandler> getOnlineClients() {
    return new ArrayList<ClientHandler>(onlineClients.values());
    }

    public boolean isOnline(String user) {
        if  (onlineClients.containsKey(user)) {
            return true;
        }
        return false;
    }
}
