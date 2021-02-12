/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peerToPeer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
/**
 *
 * @author shb96
 */
public class ServerThread extends Thread 
{
    private boolean newMessage;
    private String message;
    private ServerSocket serverSocket;
    private Set<ServerThreadThread> serverThreadThreads = new HashSet<ServerThreadThread>();
    
    public ServerThread(String portNumb) throws IOException
    {
        serverSocket = new ServerSocket(Integer.valueOf(portNumb));
        newMessage = false;
        message = null;
    }
    
    @Override
    public void run() 
    {
        try 
        {
            while(true) 
            {
                ServerThreadThread serverThreadThread = new ServerThreadThread(serverSocket.accept(), this);
                serverThreadThreads.add(serverThreadThread);
                serverThreadThread.start();
            }
        } 
        catch(Exception e)
        {
            e.printStackTrace();
        }		
    }
    
    public boolean isBlockChainServer()
    {
        return serverSocket.getLocalPort() == 4441;
    }
    
    public void sendMessage(String message) {
        try { 
            serverThreadThreads.forEach((ServerThreadThread t) -> {
                t.getPrintWriter().println(message);
            });
        } 
        catch(Exception e)
        {
            e.printStackTrace(); 
        }		
    }
    
    public void setMessage(String m)
    {
        this.message = m;
    }
    public void setNewMessage(boolean b)
    {
        newMessage = true;
    }
    
    public String getMessage()
    {
        return this.message;
    }
    
    public boolean getNewMessage()
    {
        return newMessage;
    }
    
    public Set<ServerThreadThread> getServerThreadThreads(){return serverThreadThreads; }
}
