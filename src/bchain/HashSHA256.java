/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author shb96
 */
public class HashSHA256 {
    
    //Apply hash SHA-256 on string
    public static String sha256(String string)
    {

        try {
            MessageDigest mdigest;
            mdigest = MessageDigest.getInstance("SHA-256");
            mdigest.update(string.getBytes());
            StringBuffer hexString = new StringBuffer(); 

            //Convert from byte to hex string format
            for(byte byt : mdigest.digest()) 
            {
                hexString.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
            }

            return hexString.toString();
        } 
        catch(NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    
}
