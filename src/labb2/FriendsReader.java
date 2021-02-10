/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author André Nordlund
 * @date 2021-02-10
 * @course name Java 2
 * @Lab number 1
 */
public class FriendsReader {
    private List<Friend> friends = new ArrayList<Friend>();
    private String workingPath;
    public FriendsReader(){
        try
        {  
            this.workingPath = System.getProperty("user.dir");
            File file=new File(this.workingPath+"\\logs\\friends.list");    //creates a new file instance  
            FileReader fr=new FileReader(file);   //reads the file  
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
            String line; 
            while((line=br.readLine())!=null)  {
                Friend currentFriend = new Friend();
                if(line.indexOf('>') != -1){
                    currentFriend.setNick(line.substring(1,line.indexOf('>')));
                    if(line.indexOf(']') != -1){
                        currentFriend.setNick(line.substring(1,line.indexOf('[')));
                        currentFriend.setTag(line.substring(line.indexOf('['),line.indexOf(']')+1));
                    }
                }
                line=br.readLine();
                currentFriend.setName(line.substring(line.indexOf(']')+1));
                line=br.readLine();
                currentFriend.setIp(line.substring(line.indexOf(']')+1));
                line=br.readLine();
                currentFriend.setImage(line.substring(line.indexOf(']')+1));
                friends.add(currentFriend);
            }
            Collections.sort(friends, new Sortbynick());
        }
        catch (FileNotFoundException ex) 
        {
            System.out.println("Not found");
        } 
        catch (IOException ex) 
        {
            System.out.println(ex);
        }
    }
    public void GetFriends(){
        for(int i = 0; i < friends.size(); i++){
            System.out.println(friends.get(i).getNick()+" "+friends.get(i).getTag());
            System.out.println(friends.get(i).getName());
            System.out.println(friends.get(i).getIp());
            System.out.println(friends.get(i).getImage());
        }
    }
    public List<Friend> getFriendList(){
        return friends;
    }
}
