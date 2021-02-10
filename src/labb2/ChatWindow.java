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
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 *
 * @author André
 */
public class ChatWindow extends JPanel{
    private JPanel chat = new JPanel();
    JTextArea chatText = new JTextArea("test\ntest2");
    JLabel chatLabel = new JLabel("Chat with");
    public ChatWindow(){
        Border blackline = BorderFactory.createLineBorder(Color.black);
        
        chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
        //chat.setBorder(blackline);

        chatText.setLineWrap(true);
        chatText.setWrapStyleWord(true);
        chatText.setBorder(blackline);
        chatText.setEditable(false);

        chatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        chat.add(chatLabel);
        chat.add(chatText);
        chat.setPreferredSize(new Dimension(310, 140));
    }
    public JPanel getWindow(){
        return chat;
    }
}
