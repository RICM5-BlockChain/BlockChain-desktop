package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class CSV {
	
	
	public static List<Student> readCSV(File file){
		LinkedList<Student> ls = new LinkedList<Student>();
		if(!(file.canRead())){
			System.out.println("Impossible de lire le fichier");
			 return null;
		}
		
		try{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String[] IDs;
			String sCurrentLine;
			sCurrentLine = br.readLine();	
			IDs =sCurrentLine.split(",");
	
			while ((sCurrentLine = br.readLine()) != null) {
				Student s = new Student();
				String[] Values = sCurrentLine.split(",");
				for(int i=0;i<Values.length;i++){
					s.set(IDs[i], Values[i]);
				}
				ls.add(s);
			}
			
			br.close();
			}
		
		
		catch (Exception e){
			
		}
		
		return ls;
	}

}
