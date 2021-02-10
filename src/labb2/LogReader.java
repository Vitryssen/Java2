/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author André Nordlund
 * @date 2021-02-10
 * @course name Java 2
 * @Lab number 2
 */
public class LogReader {
    private List<String> lines = new ArrayList<String>();
    private List<String> orgLines = new ArrayList<String>();
    private String workingPath;
    private Map<String, List<String>> userChats = new HashMap<String, List<String>>(); 
    public void readFile(String fileUrl){
        try
        {  
            workingPath = System.getProperty("user.dir");
            File file=new File(workingPath+"\\logs\\"+fileUrl+".log");    //creates a new file instance  
            FileReader fr=new FileReader(file);   //reads the file  
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
            String line; 
            String username;
            while((line=br.readLine())!=null)  {
                String tag = "";
                String text = line.substring(line.indexOf('>')+1);
                if(line.indexOf(']') != -1){
                    username = line.substring(1,line.indexOf('['));
                    tag = line.substring(line.indexOf('['),line.indexOf(']')+1);
                }
                else{
                    username = line.substring(1, line.indexOf('>'));
                }
                /*lines.add(username);
                lines.add(tag);
                lines.add(text); //Unused separation of message information
                */
                orgLines.add(line);
            }
            userChats.put(fileUrl, orgLines); //Saves the chat to the given username
            orgLines = new ArrayList<String>(); //And empties temporary list
        }
        catch (FileNotFoundException ex) 
        {
            orgLines.add("Not found");
            userChats.put(fileUrl, orgLines);
            orgLines = new ArrayList<String>();
        } 
        catch (IOException ex) 
        {
            System.out.println(ex);
        }
    }
    public List<String> getOrgHistory(){
        return orgLines;
    }
    public boolean chatExists(String nickname){
        if(userChats.containsKey(nickname)) //If the map contains given nick return true
            return true;
        return false;
    }
    public List<String> getUserChat(String nickname){
        return userChats.get(nickname); //Returns chat to given nickname
    }
}
