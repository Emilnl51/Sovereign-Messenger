package com.sovereignmessenger.client;

import com.sovereignmessenger.client.core.ChatModel;
import com.sovereignmessenger.client.core.NetworkController;
import com.sovereignmessenger.client.core.ViewController;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        // SwingUtilities.invokeLater(() -> {
        //     new ChatClient().setVisible(true);
        // });
        ChatModel model = new ChatModel(); 
        NetworkController networkController = new NetworkController(model);
        ViewController viewController = new ViewController(networkController, model);

        networkController.setViewController(viewController);

        viewController.setVisible(true);
    }
}
