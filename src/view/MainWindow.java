package view;

import java.awt.BorderLayout;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.CSVFile;

public class MainWindow extends JFrame{
	
	private static final long serialVersionUID = -7973353347922125981L;
	public LeftPannel lp;
	public RightPannel rp;
	public final int MarginY = 10;
	public String title;
	
	public MainWindow(String title,int width,int height){
		super(title);
		setSize(width, height);
		
		title=title;
		
		lp = new LeftPannel(this, MarginY,300,300);
		rp = new RightPannel(this,MarginY);
		
		setLayout(new BorderLayout());
		
		add(lp,BorderLayout.CENTER);
		add(rp,BorderLayout.LINE_END);
		
		this.addWindowListener(new MainWindowListener());
		pack();
		setVisible(true);
	}
	
	public void filesDropped(List<File> lf){
		for(int i=0;i<lf.size();i++){
			rp.pd.addFile(lf.get(i));
		}
		
	}
	
	public void ValidateFiles(){
		if(rp.pd.filesSelected.isEmpty()){
			JOptionPane.showMessageDialog(this, "The selected files list is empty..","Warning",JOptionPane.INFORMATION_MESSAGE);
			return ;
		}
		
		List<CSVFile> csvList = new LinkedList<CSVFile>();
		for(int i=0;i<rp.pd.filesSelected.size();i++){
			csvList.add(new CSVFile(rp.pd.filesSelected.get(i)));
		}
		
		new ValidationWindow(title, csvList);
	}
	
	
	/*
	public void showUp(){
		List<Student> ls = new LinkedList<Student>();
		ls.clear();
		for(int i=0;i<lf.size();i++){
			ls.addAll(CSV.readCSV(lf.get(i)));
		}
		lf.clear();
		
		int nombreEtudiant = ls.size();
		System.out.println("Nombre d'étudiants présent dans le fichier : " + nombreEtudiant);
		jd.setVisible(false);
		int dialogButton = JOptionPane.showConfirmDialog(this, nombreEtudiant + " étudiants trouvés dans le fichier, cela est correct ?","Confirmation",JOptionPane.YES_NO_OPTION);
		if(dialogButton==0){
			for(int i=0;i<ls.size();i++){
				//System.out.println(ls.get(i).finalPresentation());
				String hash = PDF.exportAsPdf(ls.get(i));
				
			}
			
			Launcher.config.setAsDone(ls);
			System.out.println("taille de la liste : "+ls.size());
			
			
		}
		else{
			
		}
		
		this.setVisible(false);
		this.setVisible(true);
		this.repaint();
		jd.setVisible(true);
		
		
	}
	*/
	public void ErrorExit(){
		this.setVisible(false);
		System.exit(-1);
	}
}
