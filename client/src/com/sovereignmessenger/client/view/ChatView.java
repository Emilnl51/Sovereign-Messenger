package com.sovereignmessenger.client.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sovereignmessenger.common.NetworkPacket;
import com.sovereignmessenger.client.core.ViewController;
import com.sovereignmessenger.client.core.ViewUpdateListener;

public class ChatView extends JPanel implements ViewUpdateListener {
    private ViewController controller = null;

    private JTextArea chatArea = new JTextArea();
    private DefaultListModel<String> userListModel = new DefaultListModel<>();
    private JList<String> userList = new JList<>(userListModel);
    private JTextField inputField = new JTextField();
    private JButton sendBtn = new JButton("Skicka");
    public ChatView(ViewController controller) {
        this.controller = controller;

        setLayout(new BorderLayout());

        // Chatt-historik (Mitten)
        chatArea.setEditable(false);
        chatArea.setBackground(new Color(245, 245, 245));
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        // Online-lista (Vänster)
        userList.setBorder(BorderFactory.createTitledBorder("Online"));
        userList.setFixedCellWidth(120);
        add(new JScrollPane(userList), BorderLayout.WEST);

        // Skriv-fält (Botten)
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendBtn, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // ActionListeners
        sendBtn.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());
    }

    @Override
    public void handlePacket(NetworkPacket packet) {

    }
    
    private void sendMessage() {
        String message = inputField.getText();
        if (!message.equals("")) {
            
        }
        inputField.setText("");
    }
}
