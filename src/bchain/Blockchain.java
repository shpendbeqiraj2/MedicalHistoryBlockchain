/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchain;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import medicalReport.Report;

/**
 *
 * @author shb96
 */
public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<Block>(); 
	
	public static void main(String[] args) {
            //String patientName, String patientSurname, String doctor, String report, String analysis
		
		blockchain.add(new Block(blockchain.size()+1,"0",new Report("Verona","Avdyli","Astrit","Covid-19","Positive"),1));
		blockchain.add(new Block(blockchain.size()+1,blockchain.get(blockchain.size()-1).hash,new Report("Shpend","Beqiraj","Astrit","Covid-19","Negative"),2));
		blockchain.add(new Block(blockchain.size()+1,blockchain.get(blockchain.size()-1).hash,new Report("Ereza","Azizi","Astrit","Covid-19","Positive"),3));
		
		
		//Json views
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
		System.out.println(blockchainJson);

		System.out.println("The chains are valid: "+validChain());
		System.out.println("Testin gitignore");
		
	}
	
	//Check chains integrity
	public static Boolean validChain() {

		for(int i=blockchain.size()-1; i>0; i--) {
			Block latestBlock = blockchain.get(i);
			Block previousBlock = blockchain.get(i-1);
			
			if(!latestBlock.previousHash.equals(previousBlock.hash)) {
				System.out.println("Latest block previous hash doesn't match previous block hash");
				return false;
			}
		}

		return true;
	}
}
