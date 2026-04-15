public class ChatServer {
    public static void main(String[] args) {
        int port = 2001;

        DatabaseConnector dbConnection = new DatabaseConnector();
        DatabaseManager databaseManager = new DatabaseManager(dbConnection);
        AccountManager accountManager = new AccountManager(databaseManager);
        ClientRegistry registry = new ClientRegistry();
    }
}
