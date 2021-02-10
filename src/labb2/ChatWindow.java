/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 *
 * @author André
 */
public class ChatWindow extends JPanel{
    private JPanel chat = new JPanel();
    private JTextArea chatText = new JTextArea("");
    private JLabel chatLabel = new JLabel("Chat");
    public ChatWindow(){
        Border blackline = BorderFactory.createLineBorder(Color.black);
        
        chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));

        chatText.setLineWrap(true);
        chatText.setWrapStyleWord(true);
        chatText.setBorder(blackline);
        chatText.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(chatText);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        chatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        chat.add(chatLabel);
        chat.add(scrollPane);
        chat.setPreferredSize(new Dimension(310, 140));
    }
    public JPanel getWindow(){
        return chat;
    }
    public JTextArea getChatText(){
        return chatText;
    }
}
