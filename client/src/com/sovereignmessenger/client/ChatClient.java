package com.sovereignmessenger.client;

import com.sovereignmessenger.client.core.NetworkController;
import com.sovereignmessenger.client.core.ViewController;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        // SwingUtilities.invokeLater(() -> {
        //     new ChatClient().setVisible(true);
        // });
        NetworkController networkController = new NetworkController();
        ViewController viewController = new ViewController(networkController);

        networkController.setViewController(viewController);

        viewController.setVisible(true);
    }
}
