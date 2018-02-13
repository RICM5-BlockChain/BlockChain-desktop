package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.CSVFile;
import model.Student;

public class ValidateListElement extends JPanel {
	private static final long serialVersionUID = 2012352946406557250L;
	String fileName;
	List<Student> StudentNames;
	List<JCheckBox> ValidationList;
	
	public ValidateListElement(CSVFile file){
		this.fileName=file.name;
		this.StudentNames=file.Students;
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		ValidationList = new LinkedList<>();
		JLabel name = new JLabel("    " + fileName);
		name.setFont(new Font(Font.SERIF, Font.BOLD, 15));
		this.add(name);
		for(int i=0;i<StudentNames.size();i++){
			JPanel jp = new JPanel();
			jp.setLayout(new FlowLayout());
			JCheckBox jc = new JCheckBox();
			jc.setAlignmentX(LEFT_ALIGNMENT);
			ValidationList.add(jc);
			jp.add(jc);
			JLabel jl = new JLabel(StudentNames.get(i).getName());
			jl.setAlignmentX(LEFT_ALIGNMENT);
			jp.add(jl);
			jp.setAlignmentX(LEFT_ALIGNMENT);
			this.add(jp);
		}
		this.setAlignmentX(LEFT_ALIGNMENT);
		setAllTrue();
		
	}
	
	public List<Student> getAllValidStudent(){
		List<Student> ls = new LinkedList<Student>();
		for(int i=0;i<StudentNames.size();i++){
			if(ValidationList.get(i).isSelected()){
				ls.add(StudentNames.get(i));
			}
		}
		
		return ls;
	}
	
	public void setAllTrue(){
		for(int i=0;i<ValidationList.size();i++){
			ValidationList.get(i).setSelected(true);
		}
	}

}
