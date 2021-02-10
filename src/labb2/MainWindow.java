/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author André Nordlund
 * @date 2021-02-10
 * @course name Java 2
 * @Lab number 1
 */
public class MainWindow {
    private JFrame f;  
    LogReader publicChat = new LogReader("Eurakarte");
    TopWindow top = new TopWindow();
    ChatWindow chat = new ChatWindow();
    FriendWindow friends = new FriendWindow();
    boolean privateMode = false;
    public MainWindow(){
        f=new JFrame();  
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(top.getWindow(), BorderLayout.NORTH);
        //------------------------------------------
        JCheckBox publicButton = top.getPublicButton();
        JCheckBox privateButton = top.getPrivateButton();
        publicButton.addActionListener((ActionEvent e) -> {
            chat.getChatText().setText(""); 
            if(privateButton.isSelected()){
                privateButton.setSelected(false);
            }
            if(publicButton.isSelected()){
                for(int i = 0; i < publicChat.getOrgHistory().size(); i++){
                    chat.getChatText().append(publicChat.getOrgHistory().get(i));
                    chat.getChatText().append("\n");
                }
            }
            privateMode = false;
            f.repaint();
        });
        privateButton.addActionListener((ActionEvent e) -> {
            privateMode = !privateMode;
            if(publicButton.isSelected()){
                publicButton.setSelected(false);
            }
            chat.getChatText().setText("");
            f.repaint();
        });
        
        //------------------------------------------
        //Set bounds for panel with exit and chat buttons
        top.getShowPanel().setBounds(111,45,110,70);
        top.getExitPanel().setBounds(6,45,100,40);
        //----------------------------------------
        f.add(top.getShowPanel());
        f.add(top.getExitPanel());
        //----------------------------------------
        JPanel bottom = new JPanel();
        bottom.add(chat.getWindow(), BorderLayout.WEST);
        bottom.add(friends.getWindow(), BorderLayout.EAST);
        //------------------------------------------
        getPrivateChat();
        //-----------------------------------------
        f.add(bottom, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true); 
        f.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                chat.getWindow().setPreferredSize(new Dimension(f.getWidth()-friends.longestName()-50, f.getHeight()-80));
                friends.getWindow().setPreferredSize(new Dimension(friends.longestName()+10,f.getHeight()-80));
                f.repaint();
            }
        });
    }
    private void getPrivateChat(){
        for(int i = 0; i < friends.getFriendList().getFriendList().size(); i++){
            String currentName = friends.getFriendList().getFriendList().get(i).getNick()+friends.getFriendList().getFriendList().get(i).getTag();
            JLabel nameLabel = new JLabel(currentName);
            nameLabel.setName(currentName);
            nameLabel.addMouseListener(new MouseAdapter() { 
                public void mousePressed(MouseEvent me) { 
                    if(privateMode == true){
                        LogReader privateChat = new LogReader(me.getComponent().getName());  //Dont read file every click
                        List<String> history = privateChat.getOrgHistory();
                        chat.getChatText().setText("");
                        for(int i = 0; i < history.size(); i++){
                            chat.getChatText().append(history.get(i));
                            chat.getChatText().append("\n");
                        }
                        f.repaint();
                    }
                } 
            });
            friends.getNamePanel().add(nameLabel, BorderLayout.WEST);
        }
    }
}
