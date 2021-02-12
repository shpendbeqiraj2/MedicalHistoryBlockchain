/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peerToPeer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author shb96
 */
public class ServerThreadThread extends Thread {
    private ServerThread serverThread;
    private Socket socket;
    private PrintWriter printWriter;
    
    public ServerThreadThread (Socket socket, ServerThread serverThread) 
    {
        this.serverThread = serverThread;
        this.socket = socket;
    }
   
    
    @Override
    public void run( ) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
            while(true)
            {
                 serverThread.sendMessage(bufferedReader.readLine());
            }
               
        } 
        catch(Exception e) 
        {
            serverThread.getServerThreadThreads().remove(this); 
        }
    }
    
    public PrintWriter getPrintWriter() {return printWriter;}
    
//    public boolean isBlockChainServer()
//    {
//        return serverThread.isBlockChainServer();
//    }

//    public ServerThread getServerThread() {
//        return serverThread;
//    }
    
    
}
