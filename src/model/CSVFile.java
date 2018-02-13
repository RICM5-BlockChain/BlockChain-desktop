package model;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class CSVFile {
	public String name;
	public List<Student> Students;
	
	public CSVFile(File file){
		name = file.getName();
		Students = CSV.readCSV(file);
	}
	
	public List<Student> ChooseStudents(List<Boolean> lb){
		LinkedList<Student> ls = new LinkedList<Student>();
		if(lb.size()!= Students.size()){
			System.out.println("ERROR on choosingStudents in "+name);
		}
		for(int i=0;i<lb.size();i++){
			if(lb.get(i)){
				ls.add(Students.get(i));
			}
		}
		return ls;
	}
}
