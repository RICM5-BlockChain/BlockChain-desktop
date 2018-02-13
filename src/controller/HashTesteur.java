package controller;

import java.io.File;

import model.Hash;

public class HashTesteur {
	
	public static void main(String[] args) {
		String filePath;
		if(OSValidator.isWindows()){
			filePath = (System.getProperty("user.dir")+"/PDF/"+"Boisadam Antoine"+".pdf").replace('/','\\');	
		}
		else{
			filePath = (System.getProperty("user.dir")+"/PDF/"+"Boisadam Antoine"+".pdf").replace('\\','/');
		}
		
		System.out.println(Hash.toHex(Hash.SHA256.checksum(new File(filePath))));
		
	}
}
