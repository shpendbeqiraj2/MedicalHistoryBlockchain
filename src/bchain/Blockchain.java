/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchain;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;

import medicalReport.MikroReport;
import medicalReport.Report;
import peerToPeer.PearThread;
import peerToPeer.Peer;
import peerToPeer.ServerThread;
import peerToPeer.ServerThreadThread;

/**
 *
 * @author shb96
 */
public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    static ServerThread blockChainServer;
    static boolean mining = true;
    private static Set<PearThread> pearThreads = new HashSet<PearThread>();
    
	
    public static void main(String[] args) {
        //String patientName, String patientSurname, String doctor, String report, String analysis
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            //this is the blockchain server that will be listenng to all the transaction requests
            
            blockChainServer = new ServerThread("4441");
            blockChainServer.start();
            
            //the pear that the server will be listening to
            PearThread pear1 = new PearThread(new Socket("localhost",4442));
            pearThreads.add(pear1);
            pear1.start();
            
            
            
            //new PearThread(new Socket("localhost",4443)).start();
            //genesis block that is added manualy
            blockchain.add(new Block(blockchain.size()+1,"0",new Report("Genesis","Block","Admin","",""),1));
            mining = false;
            
            listenToRequests();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private static void listenToRequests()
    {
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        
        while(true) 
        {   
            pearThreads.forEach((PearThread t) -> {
                
                if(t.getNewMessageFromPeer() != null)
                {
                    
                    System.out.println("New message is "+t.getNewMessageFromPeer());
                    
                    JsonObject jsonReport =  new JsonParser().parse(t.getNewMessageFromPeer().toString()).getAsJsonObject();
                    String parsedReport = jsonReport.get("report").toString();
                    
                    String[] reportFields = parsedReport.split(";");
                    
                    if(reportFields.length == 4)
                    {
                        //patientName;patientSurname;report;analysis
                        Report report = new Report(reportFields[0],reportFields[1],jsonReport.get("username").toString(),reportFields[2],reportFields[3]);
                        blockchain.add(new Block(blockchain.size()+1,blockchain.get(blockchain.size()-1).hash,report,1));
                    }
                    else if(reportFields.length == 5)
                    {
                        //patientName;patientSurname;report;analysis;mikro
                        MikroReport report = new MikroReport(reportFields[0],reportFields[1],jsonReport.get("username").toString(),reportFields[2],reportFields[3],reportFields[4]);
                        blockchain.add(new Block(blockchain.size()+1,blockchain.get(blockchain.size()-1).hash,report,1));
                    }
                    else if(jsonReport.get("report").toString().trim().equalsIgnoreCase("\"validate\""))
                    {
                        System.out.println("The chains are valid: "+validChain()); 
                    }
                    else
                    {
                        System.out.println("Wrong report format!");
                    }
                    
                    System.out.println(jsonReport.get("report").toString());
                    
                    t.setNewMessageFromPeer(null);
                }
            });
               		
        }
    }
        
	
    //Check chains integrity
    public static Boolean validChain() 
    {
        for(int i=blockchain.size()-1; i>0; i--) 
        {
            Block latestBlock = blockchain.get(i);
            Block previousBlock = blockchain.get(i-1);

            if(!latestBlock.previousHash.equals(previousBlock.hash)) 
            {
                System.out.println("Latest block previous hash doesn't match previous block hash");
                return false;
            }
        }
        return true;
    }
}
