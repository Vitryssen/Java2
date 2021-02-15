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
    private List<String> orgLines = new ArrayList<String>();
    private String workingPath;
    private Map<String, List<String>> userChats = new HashMap<String, List<String>>(); 
    public void readFile(String fileUrl){
        try
        {  
            System.out.println("Reading file");
            workingPath = System.getProperty("user.dir");
            File file=new File(workingPath+"\\logs\\"+fileUrl+".log");    //creates a new file instance  
            FileReader fr=new FileReader(file);   //reads the file  
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
            String line;
            while((line=br.readLine())!=null)  {
                orgLines.add(line);
            }
            userChats.put(fileUrl, orgLines); //Saves the chat to the given username
            orgLines = new ArrayList<String>(); //And empties temporary list
        }
        catch (FileNotFoundException ex) 
        {
            orgLines.add("Not found");
            userChats.put(fileUrl, orgLines);
            orgLines = new ArrayList<>();
        } 
        catch (IOException ex) 
        {
            System.out.println(ex);
        }
    }
    public boolean chatExists(String nickname){
        //If the map contains given nick
        return userChats.containsKey(nickname);
    }
    public List<String> getUserChat(String nickname){
        return userChats.get(nickname); //Returns chat to given nickname
    }
}
