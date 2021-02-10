/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb2;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author André
 */
public class LogReader {
    private List<String> lines = new ArrayList<String>();
    private List<String> orgLines = new ArrayList<String>();
    private String workingPath;
    public LogReader(String fileUrl){
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
                lines.add(username);
                lines.add(tag);
                lines.add(text);
                orgLines.add(line);
            }
        }
        catch (FileNotFoundException ex) 
        {
            orgLines.add("Not found");
            lines.add("Not found");
        } 
        catch (IOException ex) 
        {
            System.out.println(ex);
        }
    }
    public void getHistory(){
        Iterator<String> test = lines.iterator();
        while(test.hasNext()){
            System.out.println(test.next());
        }
    }
    public List<String> getOrgHistory(){
        return orgLines;
    }
}
