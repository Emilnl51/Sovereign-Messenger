package com.sovereignmessenger.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import com.sovereignmessenger.common.NetworkPacket;
import com.sovereignmessenger.common.UserDTO;
import com.sovereignmessenger.client.core.ChatModel;
import com.sovereignmessenger.client.core.ViewController;
import com.sovereignmessenger.client.core.ViewUpdateListener;

public class ChatView extends JPanel implements ViewUpdateListener {
    private ViewController controller = null;
    private ChatModel chatModel = null;

    private JTextArea chatArea = new JTextArea();
    private DefaultListModel<String> userListModel = new DefaultListModel<>();
    private JList<String> userList = new JList<>(userListModel);
    private JTextField inputField = new JTextField();
    private JButton sendBtn = new JButton("Skicka");

    public ChatView(ViewController controller, ChatModel model) {
        this.controller = controller;
        this.chatModel = model;

        setLayout(new BorderLayout());

        // Chatt-historik (Mitten)
        chatArea.setEditable(false);
        chatArea.setBackground(new Color(245, 245, 245));
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        // Online-lista (Vänster)
        userList.setBorder(BorderFactory.createTitledBorder("Online"));
        userList.setFixedCellWidth(120);
        add(new JScrollPane(userList), BorderLayout.WEST);


        userList.setCellRenderer(new UserCellRenderer()); // Aktiverar rutorna
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // LOGIKEN FÖR KLICK:
        userList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedName = userList.getSelectedValue();
                UserDTO user = chatModel.findUserByName(selectedName);
                
                if (user != null) {
                    chatModel.setCurrentSelectedChat(user);
                    // currentChatLabel.setText("Chattar med: " + user.getUserName());
                    // Här kan du också rensa chatArea om du vill ladda ny historik
                }
            }
        });

        // Skriv-fält (Botten)
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendBtn, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // ActionListeners
        sendBtn.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());
    }

    public void updateOnlineUsers(ArrayList<UserDTO> onlineUsers) {
        chatModel.setOnlineUsers(onlineUsers);
        SwingUtilities.invokeLater(() -> {
            userListModel.clear();
            for (UserDTO u : onlineUsers) {
                if (u.getUserName().equalsIgnoreCase(chatModel.getLoggedInUser())) {
                    continue;
                }
                userListModel.addElement(u.getUserName());
            }
        });
    }

    @Override
    public void handlePacket(NetworkPacket packet) {

    }
    
    private void sendMessage() {
        String message = inputField.getText();
        if (!message.equals("")) {
            // controller.sendMessage(message);
        }
        inputField.setText("");
    }


}
