package com.sovereignmessenger.server;

import com.sovereignmessenger.common.LoginPacket;

public class AccountManager {
    private DatabaseManager databaseManager = null; 
    public AccountManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public boolean handleLoginRequest(LoginPacket loginCredentials) {
        if (loginCredentials.getUserName() != null && loginCredentials.getEncryptedPassword() != null) { 
            String passwordHash = databaseManager.getPasswordHashString(loginCredentials.getUserName());
            if (loginCredentials.getEncryptedPassword().equals(passwordHash)) {
                return true;
            }
        }
        return false;
    }
}
