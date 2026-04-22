package com.sovereignmessenger.client.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;

import com.sovereignmessenger.common.NetworkPacket;
import com.sovereignmessenger.client.core.ViewController;
import com.sovereignmessenger.client.core.ViewUpdateListener;




public class LoginView extends JPanel implements ViewUpdateListener {
    private JTextField userNameInput = new JTextField(15);
    private JPasswordField passwordInput = new JPasswordField(15);
    private JButton loginButton = new JButton("Submit");

    private ViewController controller = null;

    public LoginView(ViewController controller) {
        this.controller = controller;
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;

        add(new JLabel("Användarnamn:"), gbc);
        gbc.gridy = 1; add(userNameInput, gbc);
        gbc.gridy = 2; add(new JLabel("Lösenord:"), gbc);
        gbc.gridy = 3; add(passwordInput, gbc);
        gbc.gridy = 4; add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            controller.login(userNameInput.getText(), new String(passwordInput.getPassword()));
            System.out.println("login attempted");
        });
    }

    @Override
    public void handlePacket(NetworkPacket packet) {

    }
}

