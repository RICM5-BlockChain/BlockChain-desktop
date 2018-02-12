package model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

import javax.xml.bind.DatatypeConverter;

import com.sun.mail.iap.ByteArray;

public enum Hash {
	
    MD5("MD5"),
    SHA1("SHA1"),
    SHA256("SHA-256"),
    SHA512("SHA-512");

    private String name;

    Hash(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public static String toHex(byte[] bytes) { 
        return DatatypeConverter.printHexBinary(bytes); 
    } 
    
    public String checksum(File input) {
    	FileReader fr;
		try {
			fr = new FileReader(input);
	    	int value = fr.read();
	    	LinkedList<Character> lc = new LinkedList<Character>();
	    	while(value != -1 ){
	    		lc.add((char)(value));
	    		value = fr.read();
	    	}
	    	
	    	char[] values = new char[lc.size()];
	    	for(int i=0;i<lc.size();i++){
	    		values[i]= lc.get(i);
	    	}
	    	
	    	MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    	byte[] hash = digest.digest(new String(values).getBytes("UTF-8"));
	   	    fr.close();
	   	    return toHex(hash);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
    }
}