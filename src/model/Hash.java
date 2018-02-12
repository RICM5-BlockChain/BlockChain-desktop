package model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

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
	    	byte[] hash = String.valueOf((lc.toArray())).getBytes();
	   	    StringBuffer hexString = new StringBuffer();
	   	    for (int i = 0; i < hash.length; i++) {
	   	    String hex = Integer.toHexString(0xff & hash[i]);
	   	    if(hex.length() == 1) hexString.append('0');
	           hexString.append(hex);
	   	    }
	   	    fr.close();
	   	    return hexString.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
}