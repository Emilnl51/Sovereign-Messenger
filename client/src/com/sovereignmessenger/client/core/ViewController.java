package com.sovereignmessenger.client.core;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sovereignmessenger.client.view.ChatView;
import com.sovereignmessenger.client.view.LoginView;
import com.sovereignmessenger.common.UserDTO;



public class ViewController extends JFrame {
    private CardLayout card = null;
    private LoginView loginView = null;
    private ChatView chatView = null;
    
    private JPanel mainContainer = null;

    private NetworkController networkController = null;

    ViewUpdateListener currentView = null;

    private ChatModel chatModel = null;

    public ViewController(NetworkController networkController, ChatModel model) {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent WindowEvent) {
                System.out.println("Hanterar oväntad nedstängning");

                networkController.disconnect();
                System.exit(0);
            } 
        });

        setLocationRelativeTo(null);
        setTitle("Welcome to Sovereign Messenger");
        setSize(1000, 1000);

        card = new CardLayout();
        mainContainer = new JPanel(card);
        this.networkController = networkController;
        this.chatModel = model;

        loginView = new LoginView(this);
        chatView = new ChatView(this, chatModel);

        mainContainer.add(loginView, "LOGIN");
        mainContainer.add(chatView, "CHAT");
        add(mainContainer);
        setLoginView();
        // setChatView();
    }

    public void setLoginView() {
        card.show(mainContainer, "LOGIN");
        currentView = loginView;
        // controller.setCurrentListener();
    }

    public void setChatView() {
        card.show(mainContainer, "CHAT");
        currentView = chatView;
    }

    public void login(String userName, String password) {
        networkController.loginAttempt(userName, password);
    }

    public void updateOnlineUsers(ArrayList<UserDTO> onlineUsers) {
        chatView.updateOnlineUsers(onlineUsers);
    }
}