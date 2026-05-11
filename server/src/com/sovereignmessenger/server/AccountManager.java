package com.sovereignmessenger.server;

import com.sovereignmessenger.common.LoginPacket;

public class AccountManager {
    private DatabaseManager databaseManager = null; 
    private ClientRegistry registry = null;
    public AccountManager(DatabaseManager databaseManager, ClientRegistry registry) {
        this.databaseManager = databaseManager;
        this.registry = registry;
    }

    public boolean handleLoginRequest(LoginPacket loginCredentials) {
        if (loginCredentials.getUserName() != null && loginCredentials.getEncryptedPassword() != null) { 
            String passwordHash = databaseManager.getPasswordHashString(loginCredentials.getUserName());
            if (loginCredentials.getEncryptedPassword().equals(passwordHash) 
                && !registry.isOnline(loginCredentials.getUserName())) {
                return true;
            }
        }
        return false;
    }
}
