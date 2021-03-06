/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * @author André Nordlund
 * @date 2021-02-10
 * @course name Java 2
 * @Lab number 2
 */
public class TopWindow extends ComponentAdapter{
    private JPanel showPanel = new JPanel();
    private JPanel top = new JPanel(new GridBagLayout());
    public JButton showButton = new JButton("Show");
    public JButton fileButton = new JButton("File");
    public GridBagConstraints c = new GridBagConstraints();
    private JPanel exitPanel = new JPanel();
    private JCheckBox privateButton = new JCheckBox("Private chat");
    private JCheckBox publicButton = new JCheckBox("Public chat");
    public TopWindow(){
        Border blackline;
        blackline = BorderFactory.createLineBorder(Color.black);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        top.add(fileButton , c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       
        c.weighty = 1.0;   
        c.anchor = GridBagConstraints.PAGE_END; 
        c.insets = new Insets(0,0,0,300);  
        c.gridx = 1;       
        c.gridy = 0;       
        top.add(showButton, c);

        JButton exitButton = new JButton("Exit");

        showPanel.setBorder(blackline);
        showPanel.setVisible(false);

        exitPanel.setBorder(blackline);
        exitPanel.add(exitButton, BorderLayout.WEST);
        exitButton.setPreferredSize(new Dimension(90,30));
        exitPanel.setVisible(false);

        showPanel.add(privateButton, BorderLayout.NORTH);
        showPanel.add(publicButton, BorderLayout.SOUTH);
        
        fileButton.addActionListener((ActionEvent e) -> {
            if(exitPanel.isVisible())
                exitPanel.setVisible(false);
            else
                exitPanel.setVisible(true);
        });

        exitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        showButton.addActionListener((ActionEvent e) -> {
            if(showPanel.isVisible())
                showPanel.setVisible(false);
            else
                showPanel.setVisible(true);
        });
        top.addComponentListener(new ComponentAdapter() {
        @Override
            public void componentResized(ComponentEvent componentEvent) {
                c.insets = new Insets(0,0,0, (int) (0.70*top.getWidth()));  //Resize event for top bar
                top.add(showButton, c);
                exitPanel.setBounds(fileButton.getLocation().x, fileButton.getLocation().y+fileButton.getHeight(), fileButton.getWidth(), 50);
                showPanel.setBounds(showButton.getLocation().x, showButton.getLocation().y+showButton.getHeight(), showButton.getWidth(), 50);
                privateButton.setPreferredSize(new Dimension(showPanel.getWidth()-5,15));
                publicButton.setPreferredSize(new Dimension(showPanel.getWidth()-5,15));
                exitPanel.repaint();
                showPanel.repaint();
            }
        });
    }
    public JPanel getWindow(){
        return top;
    }
    public JPanel getShowPanel(){
        return showPanel;
    }
    public JPanel getExitPanel(){
        return exitPanel;
    }
    public JCheckBox getPublicButton(){
        return publicButton;
    }
    public JCheckBox getPrivateButton(){
        return privateButton;
    }
}
