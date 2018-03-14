package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import controller.OSValidator;

public class USB {
	
	private final static int PRIVATEKEYSIZE=9;
	public static String KeyLetter="";
	
	public static boolean removedKey(){
	    File drive = new File(KeyLetter);
	   System.out.println("keyLetter : "+KeyLetter);
	    if(drive.canRead()){
	    	int compt=0;
			while (true){
				if(!drive.canRead()){
					return true;
				}
				
				try { Thread.sleep(100); }
		        catch (InterruptedException e) { /* do nothing */ }
			}
	    }
	    else{
	    	return false;
	    }
	}
	
	public static char[] main(String[] argv){
		    String[] letters = new String[]{ "Volume/F", "A:/", "B:/", "C:/", "D:/", "E:/", "F:/", "G:/", "H:/", "I:/","J:/","K:/","L:/","M:/","N:/"};
		    File[] drives = new File[letters.length];
		    
		    boolean[] isDrive = new boolean[letters.length];

		    // init the file objects and the initial drive state
		    for ( int i = 0; i < letters.length; ++i )
		        {
		        drives[i] = new File(letters[i]);

		        isDrive[i] = drives[i].canRead();
		        }

		     System.out.println("Veuillez connecter votre USB");

		     // loop indefinitely
		     while(true)
		        {
		        // check each drive 
		        for ( int i = 0; i < letters.length; ++i )
		            {
		            boolean pluggedIn = drives[i].canRead();

		            // if the state has changed output a message
		            if ( pluggedIn != isDrive[i] )
		                {
		                if (pluggedIn){
		                	KeyLetter=letters[i];
		                    System.out.println("USB "+letters[i]+" vient d'être connecté");
		                    System.out.println("lecture des fichiers, merci de patienter de ne pas retirer la clef");
		                    String username = System.getProperty("user.name");
		                    //System.out.println("Utilisateur connect� : " + username);
		                    String filePath;
		                    if(OSValidator.isWindows()){
		        				filePath = (letters[i]).replace('/','\\');	
		        			}
		        			else{
		        				filePath = (letters[i]).replace('\\','/');
		        			}
		                    File security = new File(filePath+"security.hash");
		                    File privateKey = new File(filePath+"privateKey.hash");
		                    try {
								FileReader fr = new FileReader(security);
								char[] buff = new char[64];
								fr.read(buff);
								System.out.println(new String(buff) + " " + Hash.SHA256.getHash(username));
								if(new String(buff).equalsIgnoreCase(Hash.SHA256.getHash(username))){
									fr.close();
									fr = new FileReader(privateKey);
									buff =new char[PRIVATEKEYSIZE];
									fr.read(buff);
									fr.close();
									return buff;
								}
								else{
									fr.close();
									return null;
								}
								
							} catch (FileNotFoundException e) {
								return null;
							} catch (IOException e) {
								return null;
							}
		                    
		                }
		                else{
		                	
		                }
		                isDrive[i] = pluggedIn;
		                }
		            }

		        // wait before looping
		        try { Thread.sleep(100); }
		        catch (InterruptedException e) { /* do nothing */ }

		        }
		    }
	}
