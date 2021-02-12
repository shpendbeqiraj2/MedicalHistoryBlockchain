/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peerToPeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.json.Json;
/**
 *
 * @author shb96
 */
public class PearThread extends Thread {
    
    private BufferedReader bufferedReader;
    private String newMessageFromPeer;
    
    public PearThread(Socket socket) throws IOException
    {
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        newMessageFromPeer = null;
    }
    
    @Override
    public void run() 
    {
        boolean flag = true;
        
        while (flag) {
            try { 
                javax.json.JsonObject jsonObject = Json.createReader(bufferedReader).readObject();
                if (jsonObject.containsKey("username"))
                {
                    newMessageFromPeer = jsonObject.toString();
                    //System.out.println("["+jsonObject.getString("username")+"]: "+jsonObject.getString("report"));
                    System.out.println(jsonObject.toString());
                }
                
            } 
            catch(Exception e) 
            {
                flag = false;
                interrupt();
            }
        }
    }
    
    public String getNewMessageFromPeer() {
        return newMessageFromPeer;
    }

    public void setNewMessageFromPeer(String newMessageFromPeer) {
        this.newMessageFromPeer = newMessageFromPeer;
    }
    
    
}
