package view;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import model.CSVFile;
import model.Student;

public class ValidationWindowA extends JFrame implements ItemListener {
	
	JPanel mainPanel;
	ArrayList<CSVFile> csvFiles;
	ArrayList<ArrayList<Boolean>> choosed;
	
	
	public ValidationWindowA(String title, int width, int height, List<CSVFile> csvFiles) {
		super(title);
		setSize(width, height);
		this.csvFiles = new ArrayList<>();
		this.csvFiles.addAll(csvFiles);
		
		choosed = new ArrayList<>();
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		for (CSVFile csvFile : csvFiles) {
			JLabel csvTitle = new JLabel(csvFile.name);
			Font f = csvTitle.getFont();
			csvTitle.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
			mainPanel.add(csvTitle);
			choosed.add(new ArrayList<>());
			
			for(Student student : csvFile.Students) {
				JCheckBox studentCb = new JCheckBox(student.getName());
			    studentCb.setSelected(true);	
			    studentCb.addItemListener(this);
			    choosed.get(choosed.size()-1).add(true);

				mainPanel.add(studentCb);
			}
			mainPanel.add(new JSeparator());
		}
		
		add(mainPanel);
		
		setVisible(true);
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox source = (JCheckBox) e.getItemSelectable();
		
		for (int i = 0; i < csvFiles.size(); i++) {
			for (int j = 0; j < csvFiles.get(i).Students.size(); j++) {
				Student student = csvFiles.get(i).Students.get(j);
				if(student.getName().equals(source.getText())) {
					choosed.get(i).set(j, e.getStateChange() == ItemEvent.SELECTED);
				}
			}
		}
		
	}
}
