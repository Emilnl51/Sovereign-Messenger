package com.sovereignmessenger.client.core;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sovereignmessenger.client.view.ChatView;
import com.sovereignmessenger.client.view.LoginView;



public class ViewController extends JFrame {
    private CardLayout card = null;
    private LoginView loginView = null;
    private ChatView chatView = null;
    
    private JPanel mainContainer = null;

    private NetworkController networkController = null;

    ViewUpdateListener currentView = null;

    public ViewController(NetworkController networkController) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Welcome to Sovereign Messenger");

        setSize(1000, 1000);

        card = new CardLayout();
        mainContainer = new JPanel(card);

        loginView = new LoginView(this);
        chatView = new ChatView(this);

        mainContainer.add(loginView, "LOGIN");
        mainContainer.add(chatView, "CHAT");
        this.networkController = networkController;

        add(mainContainer);
        setLoginView();
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
}