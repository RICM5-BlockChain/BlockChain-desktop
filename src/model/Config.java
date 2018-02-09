package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class Config {
	String universityName;
	String Language;
	String logoFile;
	String signatureFile;
	String responsableName;
	List<Student> alreadyDone;
	
	public List<Student> getDone(){
		return alreadyDone;
	}
	
	public Config(){
		alreadyDone = new LinkedList<Student>();
	}
	
	public void setAsDone(List<Student> ls){
		alreadyDone.addAll(ls);
		ls.clear();
	}
	
	public String getLogo(){
		return logoFile;
	}
	
	public String getSignature(){
		return signatureFile;
	}
	
	public String SignatureQuote(){
		if(Language.equalsIgnoreCase("FR")){
			return "Signature de " + responsableName;
		}
		else if(Language.equalsIgnoreCase("EN")){
			return "Signature of " + responsableName;
		}
		
		return "";
	}
	
	public static Config getConfig(){
		Config c = new Config();
		String filePath;
		if(controller.OSValidator.isWindows()){
			filePath = (System.getProperty("user.dir")+"/config/config.conf").replace('/','\\');	
		}
		else{
			filePath = (System.getProperty("user.dir")+"/config/config.conf").replace('\\','/');
		}
		File file = new File(filePath);
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String sCurrentLine;
			sCurrentLine = br.readLine();
			String[] s = sCurrentLine.split(",");
			c.universityName = s[0];
			c.Language = s[1];
			
			if(controller.OSValidator.isWindows()){
				filePath = (System.getProperty("user.dir")+"/config/").replace('/','\\');	
			}
			else{
				filePath = (System.getProperty("user.dir")+"/config/").replace('\\','/');
			}
			c.logoFile = filePath+s[2];
			c.signatureFile = filePath+s[3];
			c.responsableName = s[4];
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return c;
	}
}
