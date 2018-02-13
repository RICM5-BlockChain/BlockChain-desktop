package view;

import java.io.File;
import java.util.List;
import javax.swing.JFrame;

public class MainWindow extends JFrame{
	
	private static final long serialVersionUID = -7973353347922125981L;
	public AlreadyDonePannel pannelDone;
	public PannelDropable pannelDrop;
	
	public MainWindow(String title,int width,int height){
		super(title);
		setSize(width, height);
		pannelDrop = new PannelDropable(this,300,300,width-150);
		
		add(pannelDrop);
		
		this.addWindowListener(new MainWindowListener());
		setVisible(true);
	}
	
	public void filesDropped(List<File> lf){
		for(int i=0;i<lf.size();i++){
			pannelDrop.addFile(lf.get(i).getName());
		}
		
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
