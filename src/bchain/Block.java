/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchain;

import java.time.Instant;
import medicalReport.Report;

/**
 *
 * @author shb96
 */
public class Block {
    
    public int blocknumber;
    public String hash;
    public String previousHash;
    public long timeStamp;
    private Report data; //Report, MikroReport
    private int nonce;


    //Block constructor
    public Block(int blocknumber, String previousHash, Report data,int difficulty) 
    {
        this.blocknumber = blocknumber;
        this.previousHash = previousHash;
        this.timeStamp = Instant.now().toEpochMilli();
        this.data = data;
        this.hash = "?????????????????????????????";
        this.hash = mineBlock(this.hash,difficulty);
    }



    //Helper function for calculating the hash
    public String calculateHash(int nonce) 
    {
        String stringtoHash = previousHash + Long.toString(timeStamp) + data + nonce;
        String hashedString = HashSHA256.sha256(stringtoHash);
        return hashedString;
    }



    public String mineBlock(String hash,int difficulty) 
    {

        //Create a string with leading zeros
        String target = new String(new char[difficulty]).replace('\0', '0');

        System.out.println("Block number : " + this.blocknumber);

        //Each time no match for hash, add increase nonce and add to the string to hash.
        while(!hash.substring(0,difficulty).equals(target)) 
        {
            nonce++;
            hash = calculateHash(nonce);
            System.out.println("hash : " + hash);
        }

        System.out.println("Block Mined!!! : " + hash+"\n");
        return hash;
    }
}
