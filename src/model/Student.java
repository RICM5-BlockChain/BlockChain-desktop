package model;

import controller.Launcher;

public class Student {
	String name;
	String INE;
	int diplomeNumber=0;
	int annee=0;
	String diplomeName;
	
	
	
	public Student(String name,String INE,int diplomeNumber, int annee, String diplomeName){
		this.name = name;
		this.INE = INE;
		this.diplomeNumber= diplomeNumber;
		this.annee = annee;
		this.diplomeName=diplomeName;
	}
	
	public Student(){
		
	}
	
	public void set(String id,String value){
		switch(id){
			case "nom":
				if(name != null){
					name = value+ " " + name;
				}
				else name = value;
			break;
			
			case "prenom":
				if(name != null){
					name = name +" "+ value;
				}
				else name = value;
			break;
			
			case "INE":
				INE = value;
			break;
			
			case "numero de diplome":
				diplomeNumber = Integer.parseInt(value);
			break;
			
			case "nom prenom":
				name = value;
			break;
			
			case "annee":
				annee = Integer.parseInt(value);
			break;
			
			case "diplome":
				diplomeName = value;
			break;
			
			case "filiere":
				diplomeName = value;
			break;
			
			
			
		}
	}
	
	public boolean isValid(){
		return (diplomeName!=null);
	}
	
	public String presentation(){
		return name+" " + INE + " "+ diplomeNumber +  " "+ annee + " "+ diplomeName;
	}
	
	public String getName(){
		return name;
	}
	
	public String finalPresentation(){
		String phrase="";
		
		if(Launcher.config.Language.equalsIgnoreCase("FR")){
			phrase = Launcher.config.universityName + " certifie que l'étudiant ";
			
			if(name !=null){
				phrase += name+" ";
			}
			
			if(INE != null){
				phrase += "identifié par l'INE : " + INE + " ";
			}
			
			if(annee != 0){
				phrase += "à obtenu en " + annee;
				if(diplomeName!=null){
					phrase += " le diplome " + diplomeName;
				}
			}
			else{
				if(diplomeName != null){
					phrase += "à obtenu le diplome " + diplomeName;		
				}
			}
			
			if(diplomeNumber != 0){
				phrase += ", numéro du diplomé : " + diplomeNumber;
			}
		}
		else if(Launcher.config.Language.equalsIgnoreCase("EN")){
			phrase = Launcher.config.universityName + " certify that the student ";
			
			if(name !=null){
				phrase += name+" ";
			}
			
			if(INE != null){
				phrase += "identified by the number : " + INE + " ";
			}
			
			if(annee != 0){
				phrase += "got in " + annee;
				if(diplomeName!=null){
					phrase += " the diploma " + diplomeName;
				}
			}
			else{
				if(diplomeName != null){
					phrase += "graduated " + diplomeName;		
				}
			}
			
			if(diplomeNumber != 0){
				phrase += ", diploma number : " + diplomeNumber;
			}
		}
		
		return phrase;
	}
}
